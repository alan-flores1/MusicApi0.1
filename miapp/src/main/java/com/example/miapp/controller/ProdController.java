package com.example.miapp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.miapp.model.Producto;
import com.example.miapp.repository.ProdRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;

@Table(name = "productos")
@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
@Tag(name = "Productos", description = "Operaciones relacionadas a productos")
public class ProdController {

    private final ProdRepository prodRepository;

    public ProdController(ProdRepository prodRepository) {
        this.prodRepository = prodRepository;
    }

    // Obtener todos los libros
    @GetMapping
    public List<Producto> getAllBooks() {
        return prodRepository.findAll();
    }

    // Obtener libro por ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener todos los productos", description = "Obtiene una lista con todos los productos")
    public Producto getBookById(@PathVariable Long id) {
        return prodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    // Crear un nuevo libro
    @PostMapping
    public Producto createBook(@RequestBody Producto prod) {
        return prodRepository.save(prod);
    }

    // Actualizar libro existente
    @PutMapping("/{id}")
    @Operation(summary = "", description = "Obtiene una lista con todos los productos")
    public Producto updateBook(@PathVariable Long id, @RequestBody Producto prodDetails) {
        Producto prod = prodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        prod.setNombre(prodDetails.getNombre());
        prod.setPrecio(prodDetails.getPrecio());
        prod.setCategoria(prodDetails.getCategoria());
        return prodRepository.save(prod);
    }

    // Eliminar libro
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        prodRepository.deleteById(id);
    }
}
