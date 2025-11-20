package com.example.demo.usuario.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	private Integer usuario_id;
	private String nombre;
	private String apellido_paterno;
	private String apellido_materno;
	private String correo_usuario;
	
	@JsonIgnore
	private String contrasena;
	private String estado_usuario;
	
	private String fecha_creacion;
	private String fecha_actualizacion;
	
	private Perfil perfil;
	
}
