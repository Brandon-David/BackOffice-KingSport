package com.example.demo.direccion.repository;

import com.example.demo.direccion.model.Direccion;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RMDireccion implements RowMapper<Direccion> {

	   @Override
	    public Direccion mapRow(ResultSet rs, int rowNum) throws SQLException {
	        return Direccion.builder()
	                .direccion_id(rs.getInt("direccion_id"))
	                .cliente_id(rs.getInt("cliente_id"))
	                .nombre_direccion(rs.getString("nombre_direccion"))
	                .pais(rs.getString("pais"))
	                .codigo_postal(rs.getString("codigo_postal"))
	                .calle(rs.getString("calle"))
	                .numero_exterior(rs.getString("numero_exterior"))
	                .numero_interior(rs.getString("numero_interior"))
	                .referencias(rs.getString("referencias"))
	                .colonia(rs.getString("colonia"))
	                .municipio(rs.getString("municipio"))
	                .estado(rs.getString("estado"))
	                .telefono(rs.getString("telefono"))
	                .fecha_creacion(rs.getString("fecha_creacion"))
	                .fecha_actualizacion(rs.getString("fecha_actualizacion"))
	                .build();
	    }
	   
}
