package org.generation.hermedia.controller;

import org.generation.hermedia.model.User;
import org.generation.hermedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // permite acceso desde frontend
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Registrar usuario
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Obtener todos los usuarios (opcional, útil para pruebas)
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
