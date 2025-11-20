package com.example.miapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.miapp.model.detallePago;

import java.util.List;

public interface detallePagoRepository extends JpaRepository<detallePago, Long> {

    List<detallePago> findByCorreoUsuario(String correoUsuario);
}
