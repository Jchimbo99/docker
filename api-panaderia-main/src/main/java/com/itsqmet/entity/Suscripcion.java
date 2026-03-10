package com.itsqmet.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Suscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, max = 50)
    private String nombre;

    @Email()
    @Size(min = 5, max = 100)
    private String correo;

    @Size(max = 20)
    private String telefono;

    @Size(min = 5, max = 255)
    private String password;

    private String confirmarPassword;

    private boolean aceptaComunicaciones;

}
