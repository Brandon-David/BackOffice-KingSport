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
public class Opcion_entrega {

    private Integer opcion_entrega_id;
    private String nombre;
    private Double precio;
    private String tiempo_estimado;  // VARCHAR en BD
    private String tipo;             // ENUM en BD → String en modelo
    private String estado;           // ENUM en BD → String en modelo

    @JsonIgnore
    private String fecha_creacion;

    @JsonIgnore
    private String fecha_actualizacion;
}
