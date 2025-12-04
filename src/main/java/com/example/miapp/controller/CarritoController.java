package com.example.miapp.controller;

import com.example.miapp.model.Carrito;
import com.example.miapp.model.Producto;
import com.example.miapp.model.User;
import com.example.miapp.repository.CarritoRepository;
import com.example.miapp.repository.ProductoRepository;
import com.example.miapp.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
@Tag(name = "Carrito", description = "Operaciones relacionadas al carrito de compras")
public class CarritoController {

    private final CarritoRepository carritoRepository;
    private final UserRepository userRepository;
    private final ProductoRepository prodRepository;

    public CarritoController(CarritoRepository carritoRepository,
                             UserRepository userRepository,
                             ProductoRepository prodRepository) {
        this.carritoRepository = carritoRepository;
        this.userRepository = userRepository;
        this.prodRepository = prodRepository;
    }
    @GetMapping
    @Operation(summary = "Listar todos los carritos", description = "Retorna todos los carritos existentes (solo uso administrativo).")
    public ResponseEntity<List<Carrito>> getAll() {
        return ResponseEntity.ok(carritoRepository.findAll());
    }

    @PostMapping("/{userId}")
    @Operation(summary = "Crear carrito", description = "Crea un carrito nuevo asociado a un usuario.")
    public ResponseEntity<Carrito> crearCarrito(@PathVariable Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Carrito carrito = new Carrito();
        carrito.setUser(user);

        return ResponseEntity.ok(carritoRepository.save(carrito));
    }

    @PostMapping("/{carritoId}/agregar/{productoId}")
    @Operation(summary = "Agregar producto", description = "Agrega un producto al carrito.")
    public ResponseEntity<Carrito> agregarProducto(
            @PathVariable Long carritoId,
            @PathVariable Long productoId) {

        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        Producto producto = prodRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        carrito.getProductos().add(producto);

        return ResponseEntity.ok(carritoRepository.save(carrito));
    }

    @GetMapping("/{carritoId}")
    @Operation(summary = "Ver carrito", description = "Obtiene los productos del carrito por ID.")
    public ResponseEntity<List<Producto>> verProductos(@PathVariable Long carritoId) {

        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        return ResponseEntity.ok(carrito.getProductos());
    }

    @DeleteMapping("/{carritoId}/vaciar")
    @Operation(summary = "Vaciar carrito", description = "Elimina todos los productos del carrito.")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable Long carritoId) {

        Carrito carrito = carritoRepository.findById(carritoId)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        carrito.getProductos().clear();
        carritoRepository.save(carrito);

        return ResponseEntity.noContent().build();
    }
}
