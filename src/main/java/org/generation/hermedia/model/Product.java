package org.generation.hermedia.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Integer id;

    @Column(name = "product_name", length = 100, nullable = false, unique = true)
    private String name;

    @Column(name = "description", length = 45, nullable = false)
    private String description;

    @Column(name = "price", precision = 15, scale = 2)
    private BigDecimal price;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "dimensions", length = 45)
    private String dimensions;

    public Product() {
    }

    public Product(Integer id, String name, String description, BigDecimal price, Integer stock, String dimensions, ProductCategory category, List<OrderDetails> productDetails) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.dimensions = dimensions;
        this.category = category;
        this.productDetails = productDetails;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", dimensions='" + dimensions + '\'' +
                ", category=" + category +
                ", productDetails=" + productDetails +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(price, product.price) && Objects.equals(stock, product.stock) && Objects.equals(dimensions, product.dimensions) && Objects.equals(category, product.category) && Objects.equals(productDetails, product.productDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, stock, dimensions, category, productDetails);
    }

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategory category;

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    @OneToMany(mappedBy = "product")
    private List<OrderDetails> productDetails;

    public List<OrderDetails> getProductDetails() {
        return productDetails;
    }


    public void setProductDetails(List<OrderDetails> productDetails) {
        this.productDetails = productDetails;
    }

// Getters, Setters, equals, hashCode y toString
}
