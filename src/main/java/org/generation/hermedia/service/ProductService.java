package org.generation.hermedia.service;

import org.generation.hermedia.exception.ProductNotFoundException;
import org.generation.hermedia.model.Product;
import org.generation.hermedia.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findByName(String name){
        return productRepository.findByName(name);
    }

    // Get all products
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    // Create product
    public Product createProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

    // Find product by ID
    public Product findById(Integer id){
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException(id));
    }

    // Delete product
    public void deleteProduct(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException(id);
        }
    }

    // Update product
    public Product updateProduct(Product product, Integer id) {
        return productRepository.findById(id)
                .map(productMap -> {
                    productMap.setName(product.getName());
                    productMap.setDescription(product.getDescription());
                    return productRepository.save(productMap);
                })
                .orElseThrow(() -> new ProductNotFoundException(id));//llamamos a exception
    }
}