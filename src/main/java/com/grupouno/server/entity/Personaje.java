package com.grupouno.server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "personaje", indexes = {
        @Index(name = "uk_personaje_nombre", columnList = "nombre", unique = true)
})
@Entity
@ToString
public class Personaje {
    @Id
    @Column(name = "id", nullable = false, length = 20)
    private String id;

    @Column(name = "nombre", nullable = false, length = 15)
    private String nombre;

    @Column(name = "fuerza", nullable = false, precision = 10)
    private BigDecimal fuerza;

    @Column(name = "mana", nullable = false, precision = 10)
    private BigDecimal mana;

    @Column(name = "energia", nullable = false, precision = 10)
    private BigDecimal energia;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_especie", nullable = false)
    private Especie especie;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_jugador", nullable = false)
    private Jugador jugador;

    @Column(name = "estado_registro", precision = 1)
    private BigDecimal estadoRegistro;

    @Column(name = "fecha_modificacion", nullable = false)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDate fechaModificacion;

    public Personaje(String id, String nombre, BigDecimal fuerza, BigDecimal mana, BigDecimal energia, Especie especie, Jugador jugador, BigDecimal estadoRegistro, LocalDate fechaModificacion) {
        this.id = id;
        this.nombre = nombre;
        this.fuerza = fuerza;
        this.mana = mana;
        this.energia = energia;
        this.especie = especie;
        this.jugador = jugador;
        this.estadoRegistro = estadoRegistro;
        this.fechaModificacion = fechaModificacion;
    }

    public Personaje() {
    }

    public Personaje(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getFuerza() {
        return fuerza;
    }

    public BigDecimal getMana() {
        return mana;
    }

    public BigDecimal getEnergia() {
        return energia;
    }

    public Especie getEspecie() {
        return especie;
    }

    public Jugador getJugador() {
        return jugador;
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

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public void setEnergia(BigDecimal energia) {
        this.energia = energia;
    }

    public void setMana(BigDecimal mana) {
        this.mana = mana;
    }

    public void setFuerza(BigDecimal fuerza) {
        this.fuerza = fuerza;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(String id) {
        this.id = id;
    }
}