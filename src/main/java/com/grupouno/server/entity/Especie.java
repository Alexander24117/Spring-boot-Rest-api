package com.grupouno.server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "especie")
@ToString
public class Especie {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "nombre", nullable = false, length = 15)
    private String nombre;

    @Column(name = "estado_registro", precision = 1)
    private BigDecimal estadoRegistro;

    @Column(name = "fecha_modificacion", nullable = false)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss[.SSS][.SS][.S]")
    private LocalDate fechaModificacion;


    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public BigDecimal getEstadoRegistro() {
        return estadoRegistro;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public Especie() {
    }

    public Especie(String id, String nombre, BigDecimal estadoRegistro, LocalDate fechaModificacion) {
        this.id = id;
        this.nombre = nombre;
        this.estadoRegistro = estadoRegistro;
        this.fechaModificacion = fechaModificacion;
    }

    public Especie(String id) {
        this.id = id;
    }



    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public void setEstadoRegistro(BigDecimal estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public void setId(String id) {
        this.id = id;
    }
}
