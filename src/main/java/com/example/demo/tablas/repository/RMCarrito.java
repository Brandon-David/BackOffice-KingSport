package com.example.demo.tablas.repository;

import com.example.demo.tablas.model.Carrito;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class RMCarrito implements RowMapper<Carrito> {

	  @Override
	    public Carrito mapRow(ResultSet rs, int rowNum) throws SQLException {

	        return Carrito.builder()
	                .carrito_compra_id(rs.getInt("carrito_compra_id"))
	                .cliente_id(rs.getInt("cliente_id"))
	                .producto_id(rs.getInt("producto_id"))
	                .cantidad(rs.getInt("cantidad"))
	                .precio(rs.getDouble("precio"))
	                .subtotal(rs.getDouble("subtotal"))
	                .fecha_creacion(rs.getString("fecha_creacion"))
	                .fecha_actualizacion(rs.getString("fecha_actualizacion"))
	                .build();
	    }
	
}
