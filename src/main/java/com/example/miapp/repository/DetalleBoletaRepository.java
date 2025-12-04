package com.example.miapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.miapp.model.Boleta;
import com.example.miapp.model.DetalleBoleta;

public interface DetalleBoletaRepository extends JpaRepository<DetalleBoleta, Long> {}