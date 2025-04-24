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
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // permite acceso desde frontend
public class UserController {
    private final UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getUsers();
    }

    @DeleteMapping("/delete-user/{id}") // localhost:8080/api/v1/users/delete-user/id
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.updateUser(user, id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        //Comentamos las respuesta, evaluamos si es posible crear un nuevo usuario con los usuarios que ya existen con un if
        //Evaluar si el usuario existe mediante (email o username) en este caso será solo username
        //Si existeinfo se lanza un status 409 uy si no se lanza un 201
        if (userService.findByEmail(newUser.getEmail()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(newUser));//201
    }

    //Despues de esto es indicarle a springboot que debe solicitarle los datos al usuario con la siguiente anotacion @RequestBody que sera parte de los parametros. El body se refiiere a los atributos username etc excepto id requestbody jalara los valores de los inputs, va adentro porque apuntara a la nueva instancia que se va a crear


//Model creo la entidad y se toma como base para crear user repository luego userrepository se inyecta en userservice, despues arquitectura desacoplada


//Este metodo sigue despues de laexception de userService metodo para obtener usuario por Id(404 NotFound y 200) con un bloque de tipo try catch
//e por exception


    @GetMapping("{id}")//Llaves indican que el path puede ser cualquier n de id no es necesario colocar id

    public ResponseEntity<User> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.findById(id));

        } catch (UserNotFoundException e) {

            return ResponseEntity.notFound().build();

        }

    }
    // Método para recuperar por Email
    @GetMapping("/email/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable String email) {
        User userByEmail = userService.findByEmail(email);

        if(userByEmail== null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userByEmail);
    }
}