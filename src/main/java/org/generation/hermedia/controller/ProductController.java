package org.generation.hermedia.controller;

import org.generation.hermedia.exception.ProductNotFoundException;
import org.generation.hermedia.exception.UserNotFoundException;
import org.generation.hermedia.model.Product;
import org.generation.hermedia.model.User;
import org.generation.hermedia.repository.ProductRepository;
import org.generation.hermedia.repository.UserRepository;
import org.generation.hermedia.service.ProductService;
import org.generation.hermedia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    private final ProductService productService;



    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getProducts();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product newProduct) {
        //Comentamos las respuesta, evaluamos si es posible crear un nuevo usuario con los usuarios que ya existen con un if
        //Evaluar si el usuario existe mediante (email o username) en este caso será solo username
        //Si existeinfo se lanza un status 409 uy si no se lanza un 201
        if (productService.findByName(newProduct.getName()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(newProduct));//201
    }








//Este metodo sigue despues de laexception de userService metodo para obtener usuario por Id(404 NotFoun y 200) con un bloque de tipo try catch
//e por exception


    @GetMapping("{id}")//Llaves indican que el path puede ser cualquier n de id no es necesario colocar id

    public ResponseEntity<Product> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(productService.findById(id));

        } catch (ProductNotFoundException e) {

            return ResponseEntity.notFound().build();

        }


    }

}
