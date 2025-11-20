package com.example.miapp.controller;

import com.example.miapp.model.Producto;
import com.example.miapp.repository.ProdRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier frontend
@Tag(name = "Productos", description = "Operaciones CRUD para productos")
public class ProdController {

    private final ProdRepository prodRepository;

    public ProdController(ProdRepository prodRepository) {
        this.prodRepository = prodRepository;
    }

    // 📌 Obtener todos los productos
    @GetMapping
    @Operation(summary = "Listar productos", description = "Retorna una lista con todos los productos")
    public List<Producto> getAllProductos() {
        return prodRepository.findAll();
    }

    // 📌 Obtener producto por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar producto por ID", description = "Retorna un producto si existe")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        return prodRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 📌 Crear producto
    @PostMapping
    @Operation(summary = "Crear producto", description = "Crea un nuevo producto en la base de datos")
    public ResponseEntity<Producto> createProducto(@RequestBody Producto nuevoProducto) {
        Producto guardado = prodRepository.save(nuevoProducto);
        return ResponseEntity.ok(guardado);
    }

    // 📌 Actualizar producto
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar producto", description = "Actualiza un producto existente por su ID")
    public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto prodDetails) {
        return prodRepository.findById(id)
                .map(existente -> {
                    existente.setNombre(prodDetails.getNombre());
                    existente.setPrecio(prodDetails.getPrecio());
                    existente.setDescripcion(prodDetails.getDescripcion());
                    existente.setCategoria(prodDetails.getCategoria());
                    Producto actualizado = prodRepository.save(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 📌 Eliminar producto
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
