package com.example.demo.departamento.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subcategoria {

    private Integer subcategoria_id;
    private Integer categoria_id;
    private String nombre;

    private String fecha_creacion;
    private String fecha_actualizacion;
}
