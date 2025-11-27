package com.example.demo.pedidos.repository;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.pedidos.model.Envio;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RMEnvio implements RowMapper<Envio> {

	  @Override
	    public Envio mapRow(ResultSet rs, int rowNum) throws SQLException {

	        Envio envio = new Envio();

	        envio.setEnvio_id(rs.getInt("envio_id"));
	        envio.setPedido_id(rs.getInt("pedido_id"));
	        envio.setDireccion_id(rs.getInt("direccion_id"));
	        envio.setOpcion_entrega_id(rs.getInt("opcion_entrega_id"));
	        envio.setEstado(rs.getString("estado"));
	        envio.setFecha_pendiente(rs.getString("fecha_pendiente"));
	        envio.setFecha_enviado(rs.getString("fecha_enviado"));
	        envio.setFecha_en_camino(rs.getString("fecha_en_camino"));
	        envio.setFecha_entregado(rs.getString("fecha_entregado"));
	        envio.setNumero_seguimiento(rs.getString("numero_seguimiento"));
	        
	        envio.setFecha_creacion(rs.getString("fecha_creacion"));
	        envio.setFecha_actualizacion(rs.getString("fecha_actualizacion"));

	        return envio;
	    }

	    public Envio mapEnvio(ResultSet rs, int rowNum, String prefix) throws SQLException {

	        Envio envio = new Envio();

	        envio.setEnvio_id(rs.getInt("envio_id"));
	        envio.setPedido_id(rs.getInt("pedido_id"));
	        envio.setDireccion_id(rs.getInt("direccion_id"));
	        envio.setOpcion_entrega_id(rs.getInt("opcion_entrega_id"));
	        envio.setEstado(rs.getString("estado"));
	        envio.setFecha_pendiente(rs.getString("fecha_pendiente"));
	        envio.setFecha_enviado(rs.getString("fecha_enviado"));
	        envio.setFecha_en_camino(rs.getString("fecha_en_camino"));
	        envio.setFecha_entregado(rs.getString("fecha_entregado"));
	        
	        envio.setNumero_seguimiento(rs.getString("numero_seguimiento"));

	        envio.setFecha_creacion(rs.getString(prefix + "fecha_creacion"));
	        envio.setFecha_actualizacion(rs.getString(prefix + "fecha_actualizacion"));

	        return envio;
	    }
    
}
