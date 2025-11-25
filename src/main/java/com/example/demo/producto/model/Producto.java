package com.example.demo.producto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
	
    private Integer producto_id;       
    private Integer departamento_id;    
    private Integer categoria_id;       
    private Integer subcategoria_id;   

    private String nombre;    
    private String genero;   
    private String codigo_sku;
    private String descripcion;         
    private Double precio;                       

    private Integer stock_S;
    private Integer stock_M;
    private Integer stock_L;
    private Integer stock_XL;
    private Integer stock_XXL;
    private Integer stock_XXXL;

    private Integer stock;              
    private Boolean disponibilidad;
    
    private String modelo;
    private String serie;
    private String material;
    private String tipo_tela;
    private String estilo; 
    private String dimensiones;

    private String fecha_creacion;
    private String fecha_actualizacion;
}
