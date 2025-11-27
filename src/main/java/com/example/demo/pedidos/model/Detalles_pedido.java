package com.example.demo.pedidos.model;

import com.example.demo.producto.model.Producto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Detalles_pedido {

    private Integer detalle_pedido_id;   
    private Integer pedido_id;           
    private Integer producto_id;         

    private Integer cantidad;
    private Double precio;
    private String talla;
    private String personalizacion;      
    private Double subtotal;

    private String fecha_creacion;
    private String fecha_actualizacion;
    
    private Producto producto;
}
