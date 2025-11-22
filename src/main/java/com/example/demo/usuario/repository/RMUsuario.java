package com.example.demo.usuario.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.usuario.model.Usuario;

public class RMUsuario implements RowMapper<Usuario>{

	@Override
    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Usuario usuario = new Usuario();
        
        usuario.setUsuario_id(rs.getInt("usuario_id"));			//Mapeas los valores que obtines de la bd con las variables creadas en la clase usuario
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellido_paterno(rs.getString("apellido_paterno"));
        usuario.setApellido_materno(rs.getString("apellido_materno"));
        usuario.setCorreo_usuario(rs.getString("correo_usuario"));
        usuario.setContrasena(rs.getString("contrasena"));
        usuario.setEstado_usuario(rs.getString("estado_usuario"));
        
        usuario.setFecha_creacion(rs.getString("fecha_creacion"));
        usuario.setFecha_actualizacion(rs.getString("fecha_actualizacion"));
        
        return usuario;
	}
	
	public Usuario mapUsuario(ResultSet rs, int rowNum, String prefix) throws SQLException {
		
		Usuario usuario = new Usuario();
        
        usuario.setUsuario_id(rs.getInt("usuario_id"));			//Mapeas los valores que obtines de la bd con las variables creadas en la clase usuario
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellido_paterno(rs.getString("apellido_paterno"));
        usuario.setApellido_materno(rs.getString("apellido_materno"));
        usuario.setCorreo_usuario(rs.getString("correo_usuario"));
        usuario.setContrasena(rs.getString("contrasena"));
        usuario.setEstado_usuario(rs.getString("estado_usuario"));
        
        usuario.setFecha_creacion(rs.getString(prefix + "fecha_creacion"));
        usuario.setFecha_actualizacion(rs.getString(prefix + "fecha_actualizacion"));
		
		return usuario;
	}
	
}
