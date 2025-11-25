package com.example.demo.cliente.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Favoritos {

    private Integer favoritos_id;   
    private Integer cliente_id;     
    private Integer producto_id;    

    private String fecha_creacion;
    private String fecha_actualizacion;
}
