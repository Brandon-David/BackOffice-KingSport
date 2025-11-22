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
public class Imagen_producto {

    private Integer imagen_producto_id;   // PK
    private Integer producto_id;          // FK a producto

    private String url;                   // TEXT
    private Integer orden;                // orden de la imagen
    private Boolean es_principal;         // TINYINT(1) en BD

    @JsonIgnore
    private String fecha_creacion;

    @JsonIgnore
    private String fecha_actualizacion;
}
