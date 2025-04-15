package org.generation.hermedia.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "order_details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @Column(name = "unit_price", precision = 15, scale = 2)
    private BigDecimal unitPrice;

    public OrderDetails() {
    }

    public OrderDetails(Long id, Integer quantity, BigDecimal unitPrice, Order order, User user, Product product) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.order = order;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }


    @Override
    public String toString() {
        return "ProductDetails{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", order=" + order +
                ", product=" + product +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OrderDetails that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(quantity, that.quantity) && Objects.equals(unitPrice, that.unitPrice) && Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, unitPrice, order, product);
    }

    @ManyToOne
    @JoinColumn(name = "order_id_order")
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne
    @JoinColumn(name = "product_id_product")
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }



    // Getters, Setters, equals, hashCode y toString
}
