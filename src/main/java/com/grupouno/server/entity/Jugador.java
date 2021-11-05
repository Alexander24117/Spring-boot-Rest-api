package com.grupouno.server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "jugador", indexes = {
        @Index(name = "uk_jugador_apodo", columnList = "apodo", unique = true),
        @Index(name = "uk_jugador_cuenta", columnList = "cuenta", unique = true)
})
@Entity
@ToString
public class Jugador {
    @Id
    @Column(name = "id", nullable = false, length = 20)
    private String id;

    @Column(name = "cuenta", nullable = false, length = 20)
    private String cuenta;

    @Column(name = "contrasena", nullable = false, length = 15)
    private String contrasena;

    @Column(name = "apodo", nullable = false, length = 25)
    private String apodo;

    @Column(name = "email", nullable = false, length = 25)
    private String email;

    @Column(name = "estado_registro", precision = 1)
    private BigDecimal estadoRegistro;

    @Column(name = "fecha_modificacion", nullable = false)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDate fechaModificacion;

    @OneToMany(mappedBy="jugador",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Personaje> personajes = new ArrayList<>();

    public Jugador(String id, String cuenta, String contrasena, String apodo, String email, BigDecimal estadoRegistro, LocalDate fechaModificacion, List<Personaje> personajes) {
        this.id = id;
        this.cuenta = cuenta;
        this.contrasena = contrasena;
        this.apodo = apodo;
        this.email = email;
        this.estadoRegistro = estadoRegistro;
        this.fechaModificacion = fechaModificacion;
        this.personajes = personajes;
    }

    public Jugador() {
    }

    public Jugador(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getCuenta() {
        return cuenta;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getApodo() {
        return apodo;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getEstadoRegistro() {
        return estadoRegistro;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public void setEstadoRegistro(BigDecimal estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public void setId(String id) {
        this.id = id;
    }
}