package com.example.miapp.controller;

import com.example.miapp.model.Producto;
import com.example.miapp.repository.ProductoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
@Tag(name = "Productos", description = "Operaciones CRUD para productos")
public class ProductoController {

    private final ProductoRepository prodRepository;

    public ProductoController(ProductoRepository prodRepository) {
        this.prodRepository = prodRepository;
    }

    @GetMapping
    @Operation(summary = "Listar productos", description = "Retorna una lista con todos los productos")
    public List<Producto> getAllProductos() {
        return prodRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar producto por ID", description = "Retorna un producto si existe")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        return prodRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crear producto", description = "Crea un nuevo producto en la base de datos")
    public ResponseEntity<Producto> createProducto(@RequestBody Producto nuevoProducto) {
        Producto guardado = prodRepository.save(nuevoProducto);
        return ResponseEntity.ok(guardado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto", description = "Actualiza un producto existente por su ID")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto prodDetails) {
        return prodRepository.findById(id)
                .map(existente -> {
                    existente.setNombre(prodDetails.getNombre());
                    existente.setPrecio(prodDetails.getPrecio());
                    existente.setDescripcion(prodDetails.getDescripcion());
                    existente.setImagenUrl(prodDetails.getImagenUrl());
                    existente.setCategoria(prodDetails.getCategoria()); // ahora usa el enum

                    Producto actualizado = prodRepository.save(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar producto", description = "Elimina un producto por su ID")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        if (!prodRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        prodRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
