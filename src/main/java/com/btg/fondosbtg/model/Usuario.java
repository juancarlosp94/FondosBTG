package com.btg.fondosbtg.model;

import lombok.*;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "usuarios")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    private String id;

    private String nombre;
    private double saldo;
    private String email;
    private String telefono;
    @Builder.Default
    private List<String> fondos = new ArrayList<>();

    @Builder.Default
    private List<Transaccion> transacciones = new ArrayList<>();

}
