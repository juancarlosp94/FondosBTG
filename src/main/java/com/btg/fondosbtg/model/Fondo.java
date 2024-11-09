package com.btg.fondosbtg.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fondos")
@Getter
@Setter
@Builder
public class Fondo {
        @Id
        private String id;

        private String nombre;
        private double montoMinimo;
        private String categoria;
}
