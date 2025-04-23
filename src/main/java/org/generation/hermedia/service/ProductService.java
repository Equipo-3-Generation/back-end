package org.generation.hermedia.service;


import org.generation.hermedia.exception.ProductNotFoundException;
import org.generation.hermedia.exception.UserNotFoundException;
import org.generation.hermedia.model.Product;
import org.generation.hermedia.model.Product;
import org.generation.hermedia.model.User;
import org.generation.hermedia.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
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


//listar los productos

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

   //Crear producto

    public Product createProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }
    public Product findById(Integer id){
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException(id));
    }


    //Metodo para eliminar un producto

    public void deleteProduct(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException(id);
        }

    }

    //Modificar un producto

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