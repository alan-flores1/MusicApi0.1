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

    private String nombre_user; 

    private String correo;

    private String contrasenia;

    private String region;
    
    private String comuna;

    @Enumerated(EnumType.STRING)
    private Rol rol;    

    public enum Rol {
    ADMIN,
    CLIENTE
    }

}

