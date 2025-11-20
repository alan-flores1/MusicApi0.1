package com.example.miapp.controller;

import com.example.miapp.model.detallePago;
import com.example.miapp.repository.detallePagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin(origins = "*") 
public class detallePagoController {

    @Autowired
    private detallePagoRepository detallePagoRepository;

    
    @GetMapping
    public List<detallePago> listarPagos() {
        return detallePagoRepository.findAll();
    }

    @PostMapping
    public detallePago crearPago(@RequestBody detallePago pago) {


        if (pago.getFechaCompra() == null) {
            pago.setFechaCompra(LocalDateTime.now());
        }

        return detallePagoRepository.save(pago);
    }

    @GetMapping("/usuario/{correo}")
    public List<detallePago> obtenerPagosPorCorreo(@PathVariable String correo) {
        return detallePagoRepository.findByCorreoUsuario(correo);
    }
}
