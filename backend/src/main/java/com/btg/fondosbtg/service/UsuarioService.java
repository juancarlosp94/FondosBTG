package com.btg.fondosbtg.service;

import com.btg.fondosbtg.exception.InvalidObjectIdException;
import com.btg.fondosbtg.exception.UsuarioNotFoundException;
import com.btg.fondosbtg.exception.FondoNotFoundException;
import com.btg.fondosbtg.model.Fondo;
import com.btg.fondosbtg.model.Transaccion;
import com.btg.fondosbtg.model.Usuario;
import com.btg.fondosbtg.repository.TransaccionRepository;
import com.btg.fondosbtg.repository.UsuarioRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FondoService fondoService;

    @Autowired
    private TransaccionRepository transaccionRepository;

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public String eliminarUsuario(String id) {
        ObjectId objectId = convertirAObjectId(id);

        if (usuarioRepository.existsById(objectId)) {
            usuarioRepository.deleteById(objectId);
            return "Usuario eliminado correctamente.";
        } else {
            throw new UsuarioNotFoundException("Usuario no econtrado.");
        }
    }

    public String suscribirAFondo(String usuarioId, String fondoId) {

        ObjectId usuarioObjectId = convertirAObjectId(usuarioId);
        ObjectId fondoObjectId = convertirAObjectId(fondoId);

        Usuario usuario = usuarioRepository.findById(usuarioObjectId)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));

        Fondo fondo = fondoService.obtenerFondoPorId(fondoId)
                .orElseThrow(() -> new FondoNotFoundException("Fondo no encontrado"));

        if (usuario.getSaldo() < fondo.getMontoMinimo()) {
            return "No tiene saldo disponible para vincularse al fondo " + fondo.getNombre();
        }

        actualizarUsuarioSaldo(usuario, - fondo.getMontoMinimo());
        usuario.getFondos().add(fondoId);

        Transaccion transaccion = crearTransaccion(usuarioId, fondoObjectId, "SUBSCRIPCION", fondo.getMontoMinimo());
        usuario.getTransacciones().add(transaccion);

        usuarioRepository.save(usuario);


        return "Suscripción al fondo " + fondo.getNombre() + " completada con éxito.";
    }

    public String cancelarSuscripcion(String usuarioId, String fondoId) {

        ObjectId usuarioObjectId = convertirAObjectId(usuarioId);
        ObjectId fondoObjectId = convertirAObjectId(fondoId);

        Usuario usuario = usuarioRepository.findById(usuarioObjectId)
                .orElseThrow(() -> new UsuarioNotFoundException("Usuario no encontrado"));

        Fondo fondo = fondoService.obtenerFondoPorId(fondoId)
                .orElseThrow(() -> new FondoNotFoundException("Fondo no encontrado"));


        actualizarUsuarioSaldo(usuario, fondo.getMontoMinimo());
        usuario.getFondos().remove(fondoId);

        Transaccion transaccion = crearTransaccion(usuarioId, fondoObjectId, "CANCELACION", fondo.getMontoMinimo());
        usuario.getTransacciones().add(transaccion);

        usuarioRepository.save(usuario);

        return "Cancelación del fondo " + fondo.getNombre() + " realizada con éxito.";
    }

    private ObjectId convertirAObjectId(String id) {
        try {
            return new ObjectId(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidObjectIdException("Formato de Id invalido. Por favor proporcione un usuarioId y fondoId validos.");
        }
    }

    private void actualizarUsuarioSaldo(Usuario usuario, double monto) {
        usuario.setSaldo(usuario.getSaldo() + monto);
    }

    private Transaccion crearTransaccion(String usuarioId, ObjectId fondoId, String tipo, double monto) {
        Transaccion transaccion = new Transaccion();
        transaccion.setUsuarioId(usuarioId);
        transaccion.setFondoId(fondoId);
        transaccion.setTipo(tipo);
        transaccion.setMonto(monto);
        transaccion.setFecha(LocalDateTime.now());
        transaccionRepository.save(transaccion);
        return transaccion;
    }
}
