package com.example.demo.tablas.repository;

import com.example.demo.tablas.model.Pedido;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RMPedido implements RowMapper<Pedido> {

    @Override
    public Pedido mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Pedido.builder()
                .pedido_id(rs.getInt("pedido_id"))
                .cliente_id(rs.getInt("cliente_id"))
                .estado(rs.getString("estado"))
                .subtotal(rs.getDouble("subtotal"))
                .costo_envio(rs.getDouble("costo_envio"))
                .total(rs.getDouble("total"))
                .fecha_creacion(rs.getString("fecha_creacion"))
                .fecha_actualizacion(rs.getString("fecha_actualizacion"))
                .build();
    }
}
