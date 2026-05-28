package com.pasteleria.velazquez.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor que 0")
    private Integer cantidad;

    @NotNull(message = "El tipo de producto es obligatorio")
    @Enumerated(EnumType.STRING)
    private TipoProducto tipoProducto;

    private LocalDate fecha1;
    private LocalDate fecha2;

    // 🔥 RELACIÓN CORRECTA
    @ManyToOne
    @JoinColumn(name = "contacto_id", foreignKey = @ForeignKey(name = "fk_pedido_contacto"))
    private Contacto contacto;

    // CONSTRUCTOR
    public Pedido(Integer cantidad, TipoProducto tipoProducto, Contacto contacto) {

        this.cantidad = cantidad;
        this.tipoProducto = tipoProducto;
        this.fecha1 = LocalDate.now();
        this.fecha2 = this.fecha1.plusDays(10);
        this.contacto = contacto;
    }

    public Pedido() {
    }

    // GETTERS Y SETTERS

    public Long getId() {
        return id;
    }

   

    public Integer getCantidad() {
        return cantidad;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public LocalDate getFecha1() {
        return fecha1;
    }

    public LocalDate getFecha2() {
        return fecha2;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public void setFecha1(LocalDate fecha1) {
        this.fecha1 = fecha1;
    }

    public void setFecha2(LocalDate fecha2) {
        this.fecha2 = fecha2;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }
}