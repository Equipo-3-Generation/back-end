package org.generation.hermedia.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long idOrder;
    @Column(name = "order_date", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime orderDate;
    @Column(name = "delivery_date", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime deliveryDate;
    @Column(length = 20, nullable = false)
    private String status;

    public Order() {
    }

    public Order(Long idOrder, LocalDateTime orderDate, LocalDateTime deliveryDate, String status) {
        this.idOrder = idOrder;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Order order)) return false;
        return Objects.equals(idOrder, order.idOrder) && Objects.equals(orderDate, order.orderDate) && Objects.equals(deliveryDate, order.deliveryDate) && Objects.equals(status, order.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, orderDate, deliveryDate, status);
    }

    //Relación de Order con User N:1

    @ManyToOne
    @JoinColumn(name = "user_id_user")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
