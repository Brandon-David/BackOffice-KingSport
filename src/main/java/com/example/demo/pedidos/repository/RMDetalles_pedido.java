package com.example.demo.pedidos.repository;


import org.springframework.jdbc.core.RowMapper;

import com.example.demo.pedidos.model.Detalles_pedido;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RMDetalles_pedido implements RowMapper<Detalles_pedido> {

    @Override
    public Detalles_pedido mapRow(ResultSet rs, int rowNum) throws SQLException {

        Detalles_pedido detalles = new Detalles_pedido();

        detalles.setDetalle_pedido_id(rs.getInt("detalle_pedido_id"));
        detalles.setPedido_id(rs.getInt("pedido_id"));
        detalles.setProducto_id(rs.getInt("producto_id"));
        detalles.setCantidad(rs.getInt("cantidad"));
        detalles.setPrecio(rs.getDouble("precio"));
        detalles.setTalla(rs.getString("talla"));
        detalles.setPersonalizacion(rs.getString("personalizacion"));
        detalles.setSubtotal(rs.getDouble("subtotal"));

        detalles.setFecha_creacion(rs.getString("fecha_creacion"));
        detalles.setFecha_actualizacion(rs.getString("fecha_actualizacion"));

        return detalles;
    }

    public Detalles_pedido mapDetallesPedido(ResultSet rs, int rowNum, String prefix) throws SQLException {

        Detalles_pedido detalles = new Detalles_pedido();

        detalles.setDetalle_pedido_id(rs.getInt("detalle_pedido_id"));
        detalles.setPedido_id(rs.getInt("pedido_id"));
        detalles.setProducto_id(rs.getInt("producto_id"));
        detalles.setCantidad(rs.getInt("cantidad"));
        detalles.setPrecio(rs.getDouble("precio"));
        detalles.setTalla(rs.getString("talla"));
        detalles.setPersonalizacion(rs.getString("personalizacion"));
        detalles.setSubtotal(rs.getDouble("subtotal"));

        detalles.setFecha_creacion(rs.getString(prefix + "fecha_creacion"));
        detalles.setFecha_actualizacion(rs.getString(prefix + "fecha_actualizacion"));

        return detalles;
    }
    
}