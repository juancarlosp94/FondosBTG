package com.btg.fondosbtg.repository;

import com.btg.fondosbtg.model.Transaccion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransaccionRepository extends MongoRepository<Transaccion, String> {
    List<Transaccion> findByUsuarioId(String usuarioId);
}
