package com.example.miapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity // Esta clase se convierte en tabla
@Data // Lombok genera getters/setters/toString/etc.
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")

public class User {
    
    @Id // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incrementa el id
    private Long id;

    private String nombre_user; // Columna title
    private String contrasenia; // Columna author

    @Enumerated(EnumType.STRING)
    private Rol rol = Rol.CLIENTE;

}

