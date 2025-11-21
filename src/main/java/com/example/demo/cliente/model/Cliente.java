package com.example.demo.cliente.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
	
    private Integer cliente_id;
    private Integer direccion_principal_id;
	private String nombre_completo;
	private String correo;
	
	@JsonIgnore
	private String contrasena;
	
	private String fecha_creacion;
	private String fecha_actualizacion;
	
	
	
}
