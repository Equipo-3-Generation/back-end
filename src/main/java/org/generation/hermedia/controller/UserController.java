package org.generation.hermedia.controller;

import org.generation.hermedia.exception.UserNotFoundException;
import org.generation.hermedia.model.User;
import org.generation.hermedia.repository.UserRepository;
import org.generation.hermedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/users")
@CrossOrigin(origins = "*") // permite acceso desde frontend
public class UserController {
    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Get all users
    @GetMapping
    public List<User> getAll() {
        return userService.getUsers();
    }

    // Delete user by ID
    @DeleteMapping("/delete-user/{id}") // localhost:8080/api/v2/users/delete-user/id
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete user by ID
    @PutMapping("/update-user/{id}") // localhost:8080/api/v2/users/update-user/id
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.updateUser(user, id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Find user by username
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        if (userService.findByEmail(newUser.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409 Conflict
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(newUser)); // 201 Created
    }

    // Get user by ID
    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.findById(id));

        } catch (UserNotFoundException e) {

            return ResponseEntity.notFound().build();

        }
    }
    // Get user by Email
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email) {
        User userByEmail = userService.findByEmail(email);

        if(userByEmail== null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userByEmail);
    }
}