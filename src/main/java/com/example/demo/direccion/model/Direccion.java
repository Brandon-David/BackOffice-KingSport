package com.example.demo.direccion.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Direccion {
	
	    private Integer direccion_id;
	    private Integer cliente_id;
	    
		private String nombre_direccion;
		private String pais;
		private String codigo_postal;
		private String calle;
		private String numero_exterior;
		private String numero_interior;
		private String referencias;
		private String colonia;
		private String municipio;
		private String estado;
		private String telefono;
		
		private String fecha_creacion;
		private String fecha_actualizacion;
}
