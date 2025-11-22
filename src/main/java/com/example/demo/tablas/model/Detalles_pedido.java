package com.example.demo.tablas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Detalles_pedido {

    private Integer detalle_pedido_id;   // PK
    private Integer pedido_id;           // FK pedido
    private Integer producto_id;         // FK producto

    private Integer cantidad;
    private Double precio;
    private String talla;
    private String personalizacion;      // VARCHAR(20) aprox.
    private Double subtotal;

    @JsonIgnore
    private String fecha_creacion;

    @JsonIgnore
    private String fecha_actualizacion;
}
