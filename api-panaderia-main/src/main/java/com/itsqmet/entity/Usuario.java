package com.itsqmet.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Size(min = 3, max = 50)
    private String nombre;

    @Size(min = 3, max = 200)
    private String direccion;

    private Date fechaNacimiento;

    @Email
    @Size(min = 3, max = 30)
    private String correo;

    @Size(min = 5, max = 255)
    private String password;

    @NotNull
    private String rol;

}
