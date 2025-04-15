package org.generation.hermedia.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    @Column(length = 70, nullable = false, unique = true)
    private String name;
    @Column(length = 70, nullable = false, unique = true)
    private String lastName;
    @Column(name = "telephone_number", length = 15, nullable = false, unique = true)
    private String telephoneNumber;
    @Column(length = 70, nullable = false, unique = true)
    private String email;
    @Column(length = 15, nullable = false)
    private String password;
    @Column(length =160, nullable = false)
    private String address;
    @Column(length = 50, nullable = false)
    private String country;
    @Column(length = 50, nullable = false)
    private String region;
    @Column(length = 50, nullable = false)
    private String city;
    @Column(name = "zip_code", length = 10, nullable = false)
    private String zipCode;

    //Despues de añadir las restricciones con las anotaciones hacemos este Constructor vacio para JPA para poder hacer la tabla se añade vacio


    public User() {
    }

    public User(Long id, String name, String lastName, String telephoneNumber, String email, String password, String address, String country, String region, String city, String zipCode, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.password = password;
        this.address = address;
        this.country = country;
        this.region = region;
        this.city = city;
        this.zipCode = zipCode;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", orders=" + orders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(lastName, user.lastName) && Objects.equals(telephoneNumber, user.telephoneNumber) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(address, user.address) && Objects.equals(country, user.country) && Objects.equals(region, user.region) && Objects.equals(city, user.city) && Objects.equals(zipCode, user.zipCode) && Objects.equals(orders, user.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, telephoneNumber, email, password, address, country, region, city, zipCode, orders);
    }

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
