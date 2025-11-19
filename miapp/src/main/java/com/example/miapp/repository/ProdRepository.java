package com.example.miapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.miapp.model.Producto;

public interface ProdRepository extends JpaRepository<Producto, Long> {
}
