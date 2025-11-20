package com.example.miapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity 
@Data 
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "detalle_producto")

public class detalleProducto {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String nombreProducto;
    private Double precio;
    private String descripcionProducto;
    private String imagenUrl;

}

