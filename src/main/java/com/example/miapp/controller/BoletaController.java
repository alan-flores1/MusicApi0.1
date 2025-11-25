package com.example.miapp.controller;

import com.example.miapp.model.Boleta;
import com.example.miapp.model.Carrito;
import com.example.miapp.model.Producto;
import com.example.miapp.repository.BoletaRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/boletas")
@CrossOrigin(origins = "*")
@Tag(name = "Boletas / Pagos", description = "Operaciones para registrar y consultar pagos y boletas")
public class BoletaController {

    @Autowired
    private BoletaRepository boletaRepository;

    @GetMapping
    @Operation(summary = "Listar todos los pagos", description = "Obtiene todas las boletas registradas en el sistema.")
    public List<Boleta> listarPagos() {
        return boletaRepository.findAll();
    }

    @PostMapping
    @Operation(summary = "Registrar un pago", description = "Crea una nueva boleta, calcula el total del carrito y asigna fecha si no tiene.")
    public Boleta crearPago(@RequestBody Boleta pago) {

        Carrito carrito = pago.getCarrito();

        double total = carrito.getProductos()
                .stream()
                .mapToDouble(Producto::getPrecio)
                .sum();

        if (pago.getFechaCompra() == null) {
            pago.setFechaCompra(LocalDateTime.now());
        }

        pago.setTotal(total);

        return boletaRepository.save(pago);
    }
}
