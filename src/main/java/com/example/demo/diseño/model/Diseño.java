package com.example.demo.diseño.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Diseño {

    private Integer diseno_id;        // PK
    private Integer cliente_id;       // FK cliente

    private String nombre_equipo;
    private String posicion;          // ENUM en BD → String en modelo
    private String numeracion;        // VARCHAR(2)
    private String tipo_cuello;       // ENUM en BD → String en modelo
    private String descripcion_idea;  // VARCHAR(…)
    private String boceto;            // TEXT
    private String estado;            // ENUM(…)

    @JsonIgnore
    private String fecha_creacion;

    @JsonIgnore
    private String fecha_actualizacion;
}
