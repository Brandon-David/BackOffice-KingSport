package com.example.demo.pedidos.repository;


import org.springframework.jdbc.core.RowMapper;

import com.example.demo.pedidos.model.Pedido;

import java.sql.ResultSet;
import java.sql.SQLException;


public class RMPedido implements RowMapper<Pedido> {

    @Override
    public Pedido mapRow(ResultSet rs, int rowNum) throws SQLException {

        Pedido pedido = new Pedido();

        pedido.setPedido_id(rs.getInt("pedido_id"));
        pedido.setCliente_id(rs.getInt("cliente_id"));
        pedido.setEstado(rs.getString("estado"));
        pedido.setSubtotal(rs.getDouble("subtotal"));
        pedido.setCosto_envio(rs.getDouble("costo_envio"));
        pedido.setTotal(rs.getDouble("total"));

        pedido.setFecha_creacion(rs.getString("fecha_creacion"));
        pedido.setFecha_actualizacion(rs.getString("fecha_actualizacion"));

        return pedido;
    }

    public Pedido mapPedido(ResultSet rs, int rowNum, String prefix) throws SQLException {

        Pedido pedido = new Pedido();

        pedido.setPedido_id(rs.getInt("pedido_id"));
        pedido.setCliente_id(rs.getInt("cliente_id"));
        pedido.setEstado(rs.getString("estado"));
        pedido.setSubtotal(rs.getDouble("subtotal"));
        pedido.setCosto_envio(rs.getDouble("costo_envio"));
        pedido.setTotal(rs.getDouble("total"));

        pedido.setFecha_creacion(rs.getString(prefix + "fecha_creacion"));
        pedido.setFecha_actualizacion(rs.getString(prefix + "fecha_actualizacion"));

        return pedido;
    }
}
