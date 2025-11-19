package com.example.miapp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.miapp.model.User;
import com.example.miapp.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Permite peticiones desde frontend
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Obtener todos los libros
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Obtener libro por ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un usuario por su ID", description = "Devuelve un usuario especifico por su ID")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Crear un nuevo libro
    @PostMapping
    @Operation(summary = "Se crea un nuevo usuario", description = "Crea un nuevo usuario solicitando un body.json")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Actualizar libro existente
    @PutMapping("/{id}")
    @Operation(summary = "Actualiza los datos del usuario", description = "Se actualiza el usuario")
    public User updateUser(@PathVariable Long id, @RequestBody User bookDetails) {
        User book = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        book.setNombre_user(bookDetails.getNombre_user());
        book.setContrasenia(bookDetails.getContrasenia());
        return userRepository.save(book);
    }

    // Eliminar libro
    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar el usuario por su ID", description = "Se elimina el usuario solicitando su ID")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
