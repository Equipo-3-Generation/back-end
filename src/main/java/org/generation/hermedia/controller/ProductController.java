package org.generation.hermedia.controller;

import org.generation.hermedia.model.Product;
import org.generation.hermedia.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // Crear producto
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // Obtener todos los productos (útil para mostrar en frontend)
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
