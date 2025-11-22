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
public class Producto {

    private Integer producto_id;        // PK
    private Integer departamento_id;    // FK
    private Integer categoria_id;       // FK
    private Integer subcategoria_id;    // FK

    private String nombre;              // VARCHAR(100)
    private String descripcion;         // VARCHAR(300)
    private Double precio;              // DECIMAL(10,2)
    private String genero;              // CHAR(1)

    private Integer stock_S;
    private Integer stock_M;
    private Integer stock_L;
    private Integer stock_XL;
    private Integer stock_XXL;
    private Integer stock_XXXL;

    private Integer stock;              // total
    private Boolean disponibilidad;     // TINYINT(1)
    private String codigo_sku;

    @JsonIgnore
    private String fecha_creacion;

    @JsonIgnore
    private String fecha_actualizacion;
}
