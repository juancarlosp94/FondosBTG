package com.btg.fondosbtg.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "usuarios")
@Getter
@Setter
@Builder
public class Usuario {
    @Id
    private String id;

    private String nombre;
    private double saldo;
    private String email;
    private String telefono;
    private List<String> fondos;
    private List<Transaccion> transacciones;

}
