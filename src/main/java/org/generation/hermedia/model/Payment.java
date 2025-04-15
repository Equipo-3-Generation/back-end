package org.generation.hermedia.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @Column(name = "id_payment", length = 50)
    private String id;

    @Column(name = "payment_method", length = 45, nullable = false)
    private String paymentMethod;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @Column(name = "total_payment", precision = 15, scale = 2, nullable = false)
    private BigDecimal total;

    public Payment() {
    }

    public Payment(String id, String paymentMethod, LocalDate paymentDate, BigDecimal total, User user) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
        this.total = total;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentDate=" + paymentDate +
                ", total=" + total +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Payment payment)) return false;
        return Objects.equals(id, payment.id) && Objects.equals(paymentMethod, payment.paymentMethod) && Objects.equals(paymentDate, payment.paymentDate) && Objects.equals(total, payment.total) && Objects.equals(user, payment.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentMethod, paymentDate, total, user);
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Getters, Setters, equals, hashCode y toString
}
