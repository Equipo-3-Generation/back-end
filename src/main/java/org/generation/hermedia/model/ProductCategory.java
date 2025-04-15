package org.generation.hermedia.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

    @Entity
    @Table(name = "product_categories")
    public class ProductCategory {
        @Id
        @Column(name = "category_id", length = 50)
        private String categoryId;

        @Column(length = 50, nullable = false)
        private String material;

        @Column(length = 45, nullable = false)
        private String dimensions;

        @Column(name = "weight", precision = 15, scale = 2)
        private BigDecimal weight;

        @Lob
        private byte[] image;


        public ProductCategory() {
        }

        public ProductCategory(String categoryId, String material, String dimensions, BigDecimal weight, byte[] image, List<Product> products) {
            this.categoryId = categoryId;
            this.material = material;
            this.dimensions = dimensions;
            this.weight = weight;
            this.image = image;
            this.products = products;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getMaterial() {
            return material;
        }

        public void setMaterial(String material) {
            this.material = material;
        }

        public String getDimensions() {
            return dimensions;
        }

        public void setDimensions(String dimensions) {
            this.dimensions = dimensions;
        }

        public BigDecimal getWeight() {
            return weight;
        }

        public void setWeight(BigDecimal weight) {
            this.weight = weight;
        }

        public byte[] getImage() {
            return image;
        }

        public void setImage(byte[] image) {
            this.image = image;
        }

        @Override
        public String toString() {
            return "ProductCategory{" +
                    "categoryId='" + categoryId + '\'' +
                    ", material='" + material + '\'' +
                    ", dimensions='" + dimensions + '\'' +
                    ", weight=" + weight +
                    ", image=" + Arrays.toString(image) +
                    ", products=" + products +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof ProductCategory that)) return false;
            return Objects.equals(categoryId, that.categoryId) && Objects.equals(material, that.material) && Objects.equals(dimensions, that.dimensions) && Objects.equals(weight, that.weight) && Objects.deepEquals(image, that.image) && Objects.equals(products, that.products);
        }

        @Override
        public int hashCode() {
            return Objects.hash(categoryId, material, dimensions, weight, Arrays.hashCode(image), products);
        }

        @OneToMany(mappedBy = "category")
        private List<Product> products;

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }




        // Getters, Setters, equals, hashCode y toString
    }

