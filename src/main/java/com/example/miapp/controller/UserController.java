package com.example.miapp.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.miapp.model.User;
import com.example.miapp.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuarios", description = "CRUD de usuarios del sistema")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios", description = "Retorna una lista con todos los usuarios registrados en la base de datos.")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID", description = "Devuelve un usuario específico buscando por su ID.")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo usuario", description = "Crea un nuevo usuario recibiendo un JSON con sus datos.")
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario", description = "Actualiza el nombre y contraseña del usuario existente.")
    public User updateUser(@PathVariable Long id, @RequestBody User bookDetails) {
        User book = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        book.setNombre_user(bookDetails.getNombre_user());
        book.setContrasenia(bookDetails.getContrasenia());
        return userRepository.save(book);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario de la base de datos usando su ID.")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
