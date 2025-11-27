package com.example.demo.pedidos.model;

import java.util.List;

import com.example.demo.cliente.model.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    private Integer pedido_id;
    private Integer cliente_id;

    private String estado;
    private Double subtotal;
    private Double costo_envio;
    private Double total;

    private String fecha_creacion;
    private String fecha_actualizacion;
    
    private Cliente cliente;
    private List<Detalles_pedido> detalles_pedido;
    private Envio envio;
}
