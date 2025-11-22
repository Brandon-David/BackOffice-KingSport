package com.example.demo.tablas.repository;

import com.example.demo.tablas.model.Detalles_pedido;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RMDetalles_pedido implements RowMapper<Detalles_pedido> {

    @Override
    public Detalles_pedido mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Detalles_pedido.builder()
                .detalle_pedido_id(rs.getInt("detalle_pedido_id"))
                .pedido_id(rs.getInt("pedido_id"))
                .producto_id(rs.getInt("producto_id"))
                .cantidad(rs.getInt("cantidad"))
                .precio(rs.getDouble("precio"))
                .talla(rs.getString("talla"))
                .personalizacion(rs.getString("personalizacion"))
                .subtotal(rs.getDouble("subtotal"))
                .fecha_creacion(rs.getString("fecha_creacion"))
                .fecha_actualizacion(rs.getString("fecha_actualizacion"))
                .build();
    }
}
