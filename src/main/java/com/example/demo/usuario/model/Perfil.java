package com.example.demo.usuario.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Perfil {
	
	private Integer perfil_id;
	private String rol;
	private String descripcion_perfil;
	private String estado_perfil;
	
	private String fecha_creacion;
	private String fecha_actualizacion;
}