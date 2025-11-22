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
public class Envio {

    private Integer envio_id;
    private Integer pedido_id;
    private Integer direccion_id;
    private Integer opcion_entrega_id;

    private String estado;              // ENUM en BD
    private String numero_seguimiento;  // VARCHAR(50)

    @JsonIgnore
    private String fecha_creacion;

    @JsonIgnore
    private String fecha_actualizacion;
}
