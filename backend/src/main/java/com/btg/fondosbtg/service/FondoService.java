package com.btg.fondosbtg.service;

import com.btg.fondosbtg.exception.FondoNotFoundException;
import com.btg.fondosbtg.exception.InvalidObjectIdException;
import com.btg.fondosbtg.model.Fondo;
import com.btg.fondosbtg.repository.FondoRepository;
import com.btg.fondosbtg.repository.TransaccionRepository;
import com.btg.fondosbtg.repository.UsuarioRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FondoService {
    @Autowired
    private FondoRepository fondoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private NotificacionService notificacionService;


    public List<Fondo> obtenerTodosLosFondos() {
        return fondoRepository.findAll();
    }


    public Optional<Fondo> obtenerFondoPorId(String fondoId) {
        ObjectId objectId = convertirAObjectId(fondoId);
        return fondoRepository.findById(objectId);
    }

    public Fondo crearFondo(Fondo fondo) {
        return fondoRepository.save(fondo);
    }

    public String eliminarFondo(String fondoId) {

        ObjectId objectId = convertirAObjectId(fondoId);

        if (fondoRepository.existsById(objectId)) {
            fondoRepository.deleteById(objectId);
            return "Fondo eliminado correctamente.";
        } else {
            throw new FondoNotFoundException("Fondo no encontrado.");
        }
    }

    private ObjectId convertirAObjectId(String id) {
        try {
            return new ObjectId(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidObjectIdException("Formato de Id invalido. Por favor proporcione un usuarioId y fondoId validos.");
        }
    }
}
