package com.example.demo.cliente.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.cliente.model.Cliente;

public class RMCliente implements RowMapper<Cliente> {

	  @Override
	    public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
	        Cliente cliente = new Cliente();
	        cliente.setCliente_id(rs.getInt("cliente_id"));
	        cliente.setDireccion_principal_id(rs.getInt("direccion_principal_id"));
	        cliente.setNombre_completo(rs.getString("nombre_completo"));
	        cliente.setCorreo(rs.getString("correo"));
	        cliente.setTelefono(rs.getString("telefono"));
	        cliente.setContrasena(rs.getString("contrasena"));
	        cliente.setEstado(rs.getString("estado"));
	        
	        cliente.setFecha_creacion(rs.getString("fecha_creacion"));
	        cliente.setFecha_actualizacion(rs.getString("fecha_actualizacion"));
	        
	        return cliente;
	    }
	  
	  public Cliente mapCliente(ResultSet rs, int rowNum, String prefix) throws SQLException {
		  
	        Cliente cliente = new Cliente();
	        
	        cliente.setCliente_id(rs.getInt("cliente_id"));
	        cliente.setDireccion_principal_id(rs.getInt("direccion_principal_id"));
	        cliente.setNombre_completo(rs.getString("nombre_completo"));
	        cliente.setCorreo(rs.getString("correo"));
	        cliente.setTelefono(rs.getString("telefono"));
	        cliente.setContrasena(rs.getString("contrasena"));
	        cliente.setEstado(rs.getString("estado"));
	        
	        cliente.setFecha_creacion(rs.getString(prefix + "fecha_creacion"));
	        cliente.setFecha_actualizacion(rs.getString(prefix + "fecha_actualizacion"));
	        
	        return cliente;
	  }
	
}
