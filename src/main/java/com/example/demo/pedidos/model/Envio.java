package com.example.demo.pedidos.model;

import com.example.demo.direccion.model.Direccion;
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

    private String estado;   
    private String fecha_pendiente;
    private String fecha_enviado;
    private String fecha_en_camino;
    private String fecha_entregado;
    private String numero_seguimiento;

    private String fecha_creacion;
    private String fecha_actualizacion;
    
    private Direccion direccion;
}
