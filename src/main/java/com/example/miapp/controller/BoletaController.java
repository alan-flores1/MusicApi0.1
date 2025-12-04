package com.example.miapp.controller;

import com.example.miapp.model.*;
import com.example.miapp.repository.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/boletas")
public class BoletaController {

    private final BoletaRepository boletaRepo;
    private final DetalleBoletaRepository detalleRepo;
    private final ProductoRepository productoRepo;
    private final UserRepository userRepo;

    public BoletaController(
            BoletaRepository boletaRepo,
            DetalleBoletaRepository detalleRepo,
            ProductoRepository productoRepo,
            UserRepository userRepo
    ) {
        this.boletaRepo = boletaRepo;
        this.detalleRepo = detalleRepo;
        this.productoRepo = productoRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public List<Boleta> listar() {
        return boletaRepo.findAll();
    }

    @PostMapping("/crear/{userId}")
    public Boleta crearBoleta(@PathVariable Long userId, @RequestBody List<DetalleRequest> items) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Boleta boleta = new Boleta();
        boleta.setUser(user);
        boleta.setFechaCompra(LocalDateTime.now());

        boleta = boletaRepo.save(boleta);

        double total = 0;

        for (DetalleRequest req : items) {
            Producto producto = productoRepo.findById(req.getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            DetalleBoleta det = new DetalleBoleta();
            det.setBoleta(boleta);
            det.setProducto(producto);
            det.setCantidad(req.getCantidad());
            det.setSubtotal(producto.getPrecio() * req.getCantidad());

            total += det.getSubtotal();

            detalleRepo.save(det);
        }

        boleta.setTotal(total);
        return boletaRepo.save(boleta);
    }

    @Getter @Setter
    public static class DetalleRequest {
        private Long productoId;
        private int cantidad;
    }
}
