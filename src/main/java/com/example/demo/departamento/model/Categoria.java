package com.example.demo.departamento.model;

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
    private Integer departamento_id;
    private String nombre;

    private String fecha_creacion;
    private String fecha_actualizacion;
    
    
}
