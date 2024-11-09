package com.btg.fondosbtg.service;

import com.btg.fondosbtg.model.Fondo;
import com.btg.fondosbtg.model.Transaccion;
import com.btg.fondosbtg.model.Usuario;
import com.btg.fondosbtg.repository.TransaccionRepository;
import com.btg.fondosbtg.repository.UsuarioRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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
        ObjectId objectId;


        try {
            objectId = new ObjectId(id);

        } catch (IllegalArgumentException e) {

            return "Formato de Id invalido. Por favor proporcione un usuarioId y fondoId validos.";
        }

        if (usuarioRepository.existsById(objectId)) {
            usuarioRepository.deleteById(objectId);
            return "Usuario eliminado correctamente.";
        } else {
            return "Usuario no encontrado.";
        }
    }


    public String suscribirAFondo(String usuarioId, String fondoId) {

        ObjectId usuarioObjectId;
        ObjectId fondoObjectId;


        try {
            usuarioObjectId = new ObjectId(usuarioId);
            fondoObjectId = new ObjectId(fondoId);
        } catch (IllegalArgumentException e) {

            return "Formato de Id invalido. Por favor proporcione un usuarioId y fondoId validos.";
        }


        Usuario usuario = usuarioRepository.findById(usuarioObjectId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Optional<Fondo> fondoOpt = fondoService.obtenerFondoPorId(fondoId);
        if (fondoOpt.isEmpty()) {
            return "Fondo no encontrado";
        }

        Fondo fondo = fondoOpt.get();
        if (usuario.getSaldo() < fondo.getMontoMinimo()) {
            return "No tiene saldo disponible para vincularse al fondo " + fondo.getNombre();
        }


        usuario.setSaldo(usuario.getSaldo() - fondo.getMontoMinimo());
        usuario.getFondos().add(fondoId);



        Transaccion transaccion = new Transaccion();
        transaccion.setUsuarioId(usuarioId);
        transaccion.setFondoId(fondoObjectId);
        transaccion.setTipo("SUBSCRIPCION");
        transaccion.setMonto(fondo.getMontoMinimo());
        transaccion.setFecha(LocalDateTime.now());
        transaccionRepository.save(transaccion);
        usuario.getTransacciones().add(transaccion);

        usuarioRepository.save(usuario);

        return "Suscripción al fondo " + fondo.getNombre() + " completada con éxito.";
    }


    public String cancelarSuscripcion(String usuarioId, String fondoId) {

        ObjectId usuarioObjectId;
        ObjectId fondoObjectId;


        try {
            usuarioObjectId = new ObjectId(usuarioId);
            fondoObjectId = new ObjectId(fondoId);
        } catch (IllegalArgumentException e) {

            return "Formato de Id invalido. Por favor proporcione un usuarioId y fondoId validos.";
        }

        Optional<Fondo> fondoOpt = fondoService.obtenerFondoPorId(fondoId);
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioObjectId);

        if (fondoOpt.isPresent() && usuarioOpt.isPresent()) {
            Fondo fondo = fondoOpt.get();
            Usuario usuario = usuarioOpt.get();

            usuario.setSaldo(usuario.getSaldo() + fondo.getMontoMinimo());
            usuario.getFondos().remove(fondoId);

            Transaccion transaccion = new Transaccion();
            transaccion.setUsuarioId(usuarioId);
            transaccion.setFondoId(fondoObjectId);
            transaccion.setTipo("CANCELACION");
            transaccion.setMonto(fondo.getMontoMinimo());
            transaccion.setFecha(LocalDateTime.now());
            transaccionRepository.save(transaccion);

            usuario.getTransacciones().add(transaccion);
            usuarioRepository.save(usuario);
            return "Cancelación del fondo " + fondo.getNombre() + " realizada con éxito.";
        } else {
            return "Usuario o fondo no encontrado.";
        }
    }
}
