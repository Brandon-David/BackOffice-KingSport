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
public class Pedido {

    private Integer pedido_id;
    private Integer cliente_id;

    private String estado;      // ENUM en BD
    private Double subtotal;
    private Double costo_envio;
    private Double total;

    @JsonIgnore
    private String fecha_creacion;

    @JsonIgnore
    private String fecha_actualizacion;
}
