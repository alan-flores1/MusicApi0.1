package com.example.miapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity 
@Data 
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")

public class User {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String Nombre_user; 

    private String Correo;

    private String Contrasenia;

    private String Region;
    
    private String Comuna;

    @Enumerated(EnumType.STRING)
    private Rol rol;    

    public enum Rol {
    ADMIN,
    CLIENTE
    }

}

