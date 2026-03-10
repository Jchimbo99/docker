package com.itsqmet.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(min = 3, max = 50)
    private String nombre;

   @Size(min = 10, max = 200)
    private String descripcion;

    @Positive
    private Double precio;

    @Size(min = 3, max = 30)
    private String categoria;

    @Size(min = 5, max = 255)
    private String imagenUrl;

    private boolean nuevo;


}
