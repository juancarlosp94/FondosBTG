package com.btg.fondosbtg.repository;

import com.btg.fondosbtg.model.Usuario;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, ObjectId> {
}
