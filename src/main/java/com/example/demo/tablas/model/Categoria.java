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
public class Categoria {

    private Integer categoria_id;
    private String nombre;

    @JsonIgnore
    private String fecha_creacion;

    @JsonIgnore
    private String fecha_actualizacion;
}
