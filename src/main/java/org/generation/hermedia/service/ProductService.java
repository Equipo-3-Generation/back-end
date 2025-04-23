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
    // Crear producto
    private final ProductRepository productRepository;


    @Autowired

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;

    }

    public Product findByName(String name){
        return productRepository.findByName(name);
    }



    //Recuperar los users con metodoList recibira la clase del model no lleva parametros porque queremos todos los usuarios, osea todas las instancias de la entidad
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    //Metodo para crear un nuevo usuario debe ser el mismo tipo del model si se le debe definir parametro para que se cree un objeto en return le pedimos a userRepository el metodo save para eso le pasamos el parametro y lo que hara es guardar una entidad en ese parametro (por el momento no estamos agregando validaciones)

    public Product createProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }
    public Product findById(Integer id){
        return productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException(id));
    }



    //Este metodo se creo a partir del nuevo controller y es un metodo para recuperar un usuario por email


    //Metodo para eliminar un producto

    public void deleteProduct(Integer id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new ProductNotFoundException(id);
        }

    }

    public Product updateProduct(Product product, Integer id) {
        return productRepository.findById(id)
                .map(productMap -> {
                    productMap.setName(product.getName());
                    productMap.setDescription(product.getDescription());
                    productMap.setCategory(product.getCategory());
                    return productRepository.save(productMap);
                })
                .orElseThrow(() -> new ProductNotFoundException(id));//llamamos a exception
    }
}