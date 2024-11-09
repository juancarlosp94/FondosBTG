package com.btg.fondosbtg.repository;

import com.btg.fondosbtg.model.Fondo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FondoRepository extends MongoRepository<Fondo, ObjectId> {

}
