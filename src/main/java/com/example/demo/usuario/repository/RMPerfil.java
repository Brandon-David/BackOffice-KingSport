package com.example.demo.usuario.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.usuario.model.Perfil;

public class RMPerfil implements RowMapper<Perfil>{

	@Override
    public Perfil mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Perfil perfil = new Perfil();
        
        perfil.setPerfil_id(rs.getInt("perfil_id"));			//Mapeas los valores que obtines de la bd con las variables creadas en la clase perfil
        perfil.setRol(rs.getString("rol"));
        perfil.setDescripcion_perfil(rs.getString("descripcion_perfil"));
        perfil.setEstado_perfil(rs.getString("estado_perfil"));
        
        perfil.setFecha_creacion(rs.getString("fecha_creacion"));
        perfil.setFecha_actualizacion(rs.getString("fecha_actualizacion"));
        
        return perfil;
	}
	
	public Perfil mapPerfil(ResultSet rs, int rowNum, String prefix) throws SQLException {
		
		Perfil perfil = new Perfil();
        
        perfil.setPerfil_id(rs.getInt("perfil_id"));			//Mapeas los valores que obtines de la bd con las variables creadas en la clase perfil
        perfil.setRol(rs.getString("rol"));
        perfil.setDescripcion_perfil(rs.getString("descripcion_perfil"));
        perfil.setEstado_perfil(rs.getString("estado_perfil"));
        
        perfil.setFecha_creacion(rs.getString(prefix + "fecha_creacion"));
        perfil.setFecha_actualizacion(rs.getString(prefix + "fecha_actualizacion"));
        
        return perfil;
	}
	
}
