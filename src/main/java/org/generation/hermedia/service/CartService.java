package org.generation.hermedia.service;

import org.generation.hermedia.model.Cart;
import org.generation.hermedia.model.Product;
import org.generation.hermedia.model.User;
import org.generation.hermedia.repository.CartRepository;
import org.generation.hermedia.repository.ProductRepository;
import org.generation.hermedia.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Cart createCart(Long userId, List<Integer> productIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));

        List<Product> products = productRepository.findAllById(productIds);

        Cart cart = new Cart(user, products);
        return cartRepository.save(cart);
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }
}
