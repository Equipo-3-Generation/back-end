package org.generation.hermedia.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    @Column(length = 70, nullable = false, unique = true)
    private String nombre;
    @Column(length = 70, nullable = false, unique = true)
    private String apellido;
    @Column(length = 15, nullable = false, unique = true)
    private String numeroTelefonico;
    @Column(length = 70, nullable = false, unique = true)
    private String email;
    @Column(length = 15, nullable = false)
    private String password;
    @Column(length =160, nullable = false)
    private String direccion;
    @Column(length = 50, nullable = false)
    private String pais;
    @Column(length = 50, nullable = false)
    private String region;
    @Column(length = 50, nullable = false)
    private String ciudad;
    @Column(length = 10, nullable = false)
    private String codigoPostal;

    //Despues de añadir las restricciones con las anotaciones hacemos este Constructor vacio para JPA para poder hacer la tabla se añade vacio


    public User() {
    }

    public User(Long id, String nombre, String apellido, String numeroTelefonico, String email, String direccion, String pais, String region, String ciudad, String codigoPostal) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.numeroTelefonico = numeroTelefonico;
        this.email = email;
        this.direccion = direccion;
        this.pais = pais;
        this.region = region;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", numeroTelefonico='" + numeroTelefonico + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", pais='" + pais + '\'' +
                ", region='" + region + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                '}';

    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id) && Objects.equals(nombre, user.nombre) && Objects.equals(apellido, user.apellido) && Objects.equals(numeroTelefonico, user.numeroTelefonico) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(direccion, user.direccion) && Objects.equals(pais, user.pais) && Objects.equals(region, user.region) && Objects.equals(ciudad, user.ciudad) && Objects.equals(codigoPostal, user.codigoPostal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, numeroTelefonico, email, password, direccion, pais, region, ciudad, codigoPostal);
    }
}
