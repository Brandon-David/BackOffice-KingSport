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
public class Carrito {

	    private Integer carrito_compra_id;
	    private Integer cliente_id;
	    private Integer producto_id;

	    private Integer cantidad;
	    private Double precio;      // puedes cambiar a BigDecimal si lo prefieres
	    private Double subtotal;

	    @JsonIgnore
	    private String fecha_creacion;

	    @JsonIgnore
	    private String fecha_actualizacion;
	
}
