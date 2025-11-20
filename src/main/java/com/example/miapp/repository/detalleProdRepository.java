package com.example.miapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.miapp.model.detalleProducto;

public interface detalleProdRepository extends JpaRepository<detalleProducto, Long> {
}
