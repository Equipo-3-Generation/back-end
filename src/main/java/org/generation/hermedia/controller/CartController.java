package org.generation.hermedia.controller;

import org.generation.hermedia.model.Cart;
import org.generation.hermedia.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@CrossOrigin(origins = "*")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // Crear carrito
    @PostMapping
    public Cart createCart(@RequestBody CartRequest request) {
        return cartService.createCart(request.getUserId(), request.getProductIds());
    }

    // Obtener todos los carritos (opcional)
    @GetMapping
    public List<Cart> getAllCarts() {
        return cartService.getAllCarts();
    }

    // Clase interna para recibir el formato correcto de solicitud
    public static class CartRequest {
        private Long userId;
        private List<Integer> productIds;

        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }

        public List<Integer> getProductIds() { return productIds; }
        public void setProductIds(List<Integer> productIds) { this.productIds = productIds; }
    }
}
