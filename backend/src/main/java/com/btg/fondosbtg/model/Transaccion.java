package com.btg.fondosbtg.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "transacciones")
@Getter
@Setter
public class Transaccion {
    @Id
    private String id;

    private String usuarioId;
    private ObjectId fondoId;
    private String tipo;
    private double monto;
    private LocalDateTime fecha;



}
