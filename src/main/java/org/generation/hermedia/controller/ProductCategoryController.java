package org.generation.hermedia.controller;

import org.generation.hermedia.model.ProductCategory;
import org.generation.hermedia.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @PostMapping
    public ProductCategory createCategory(@RequestBody ProductCategory category) {
        return productCategoryRepository.save(category);
    }

    @GetMapping
    public List<ProductCategory> getAllCategories() {
        return productCategoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ProductCategory> getCategoryById(@PathVariable String id) {
        return productCategoryRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable String id) {
        productCategoryRepository.deleteById(id);
    }
}
