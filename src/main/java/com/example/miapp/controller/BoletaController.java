package com.example.miapp.controller;

import com.example.miapp.model.*;
import com.example.miapp.repository.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/boletas")
public class BoletaController {

    private final BoletaRepository boletaRepo;
    private final ProductoRepository productoRepo;
    private final UserRepository userRepo;

    public BoletaController(
            BoletaRepository boletaRepo,
            ProductoRepository productoRepo,
            UserRepository userRepo
    ) {
        this.boletaRepo = boletaRepo;
        this.productoRepo = productoRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public List<Boleta> listar() {
        return boletaRepo.findAll();
    }

    @Transactional
    @PostMapping("/crear/{userId}")
    public Boleta crearBoleta(@PathVariable Long userId, @RequestBody List<DetalleRequest> items) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Boleta boleta = new Boleta();
        boleta.setUser(user);
        boleta.setFechaCompra(LocalDateTime.now());
        boleta.setDetalles(new ArrayList<>()); 

        double total = 0;

        for (DetalleRequest req : items) {
            Producto producto = productoRepo.findById(req.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            DetalleBoleta detalle = new DetalleBoleta();
            detalle.setBoleta(boleta);
            detalle.setProducto(producto);
            detalle.setCantidad(req.getCantidad());
            detalle.setSubtotal(producto.getPrecio() * req.getCantidad());

            boleta.getDetalles().add(detalle);
            total += detalle.getSubtotal();
        }

        boleta.setTotal(total);
        return boletaRepo.save(boleta); 
    }

    public static class DetalleRequest {
        private Long productoId;
        private int cantidad;

        public Long getProductoId() { return productoId; }
        public void setProductoId(Long productoId) { this.productoId = productoId; }
        public int getCantidad() { return cantidad; }
        public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    }
}
