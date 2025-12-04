package com.example.miapp.repository;

import com.example.miapp.model.Carrito;
import com.example.miapp.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
     Carrito findByUser(User user);
}