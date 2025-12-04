package com.example.miapp.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "detalle_boleta")

public class DetalleBoleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "boleta_id")
    @JsonIgnoreProperties("detalles")
    private Boleta boleta;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private int cantidad;

    private double subtotal;
}
