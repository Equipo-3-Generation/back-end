package org.generation.hermedia.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
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

    @Column(name = "description", length = 500, nullable = false)
    private String description;

    // category
    @Column(name = "category", length = 100)
    private String category;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Column(name = "price", precision = 15, scale = 2)
    private BigDecimal price;

    // weight
    @Column(name = "weight")
    private Float weight;

    @Column(name = "dimensions", length = 45)
    private String dimensions;

    // material
    @Column(name = "materials", length = 200)
    private String materials; // PLA, ABS, PETG como texto separado por comas

    // imagen
    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(name = "customizable")
    private Boolean customizable;

    public Product() {
    }

    public Product(Integer id, String name, String description, String category, Integer stock, BigDecimal price, Float weight, String dimensions, String materials, String imageUrl, Boolean customizable, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.stock = stock;
        this.price = price;
        this.weight = weight;
        this.dimensions = dimensions;
        this.materials = materials;
        this.imageUrl = imageUrl;
        this.customizable = customizable;
        this.user = user;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getCustomizable() {
        return customizable;
    }

    public void setCustomizable(Boolean customizable) {
        this.customizable = customizable;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", weight=" + weight +
                ", dimensions='" + dimensions + '\'' +
                ", materials='" + materials + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", customizable=" + customizable +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(category, product.category) && Objects.equals(stock, product.stock) && Objects.equals(price, product.price) && Objects.equals(weight, product.weight) && Objects.equals(dimensions, product.dimensions) && Objects.equals(materials, product.materials) && Objects.equals(imageUrl, product.imageUrl) && Objects.equals(customizable, product.customizable) && Objects.equals(user, product.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, category, stock, price, weight, dimensions, materials, imageUrl, customizable, user);
    }

    @ManyToOne
    @JoinColumn(name = "product_id_user")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
