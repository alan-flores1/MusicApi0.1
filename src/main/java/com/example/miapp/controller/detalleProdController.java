package com.example.miapp.controller;

import com.example.miapp.repository.detalleProdRepository;
import com.example.miapp.model.detalleProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalleProducto")
@CrossOrigin(origins = "https://music-online-lilac.vercel.app/?fbclid=PAVERFWAOL_TRleHRuA2FlbQIxMABzcnRjBmFwcF9pZA8xMjQwMjQ1NzQyODc0MTQAAaenhgJRKqHt92HT9OwtLV1DHc1476luFtMj1bzDzQfLF0H0rcuGptnkcmm9EA_aem_kbBO9zk90Dc1L5FtDFsu8g") 
public class detalleProdController {

    @Autowired
    private detalleProdRepository detalleProductoRepository;

    @GetMapping
    public List<detalleProducto> listarTodos() {
        return detalleProductoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<detalleProducto> obtenerPorId(@PathVariable Long id) {
        return detalleProductoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public detalleProducto crear(@RequestBody detalleProducto nuevo) {
        return detalleProductoRepository.save(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<detalleProducto> actualizar(@PathVariable Long id,
                                                      @RequestBody detalleProducto datos) {
        return detalleProductoRepository.findById(id)
                .map(existente -> {
                    existente.setNombreProducto(datos.getNombreProducto());
                    existente.setPrecio(datos.getPrecio());
                    existente.setDescripcionProducto(datos.getDescripcionProducto());
                    existente.setImagenUrl(datos.getImagenUrl());
                    detalleProducto actualizado = detalleProductoRepository.save(existente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!detalleProductoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        detalleProductoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
