package org.generation.hermedia.controller;

import org.generation.hermedia.model.OrderDetails;
import org.generation.hermedia.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order-details")
@CrossOrigin(origins = "*")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsRepository productDetailRepository;

    @PostMapping
    public OrderDetails createProductDetail(@RequestBody OrderDetails detail) {
        return productDetailRepository.save(detail);
    }

    @GetMapping
    public List<OrderDetails> getAllProductDetails() {
        return productDetailRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<OrderDetails> getProductDetailById(@PathVariable Long id) {
        return productDetailRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteProductDetail(@PathVariable Long id) {
        productDetailRepository.deleteById(id);
    }
}
