package com.example.demo.tablas.repository;

import com.example.demo.tablas.model.Envio;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RMEnvio implements RowMapper<Envio> {

    @Override
    public Envio mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Envio.builder()
                .envio_id(rs.getInt("envio_id"))
                .pedido_id(rs.getInt("pedido_id"))
                .direccion_id(rs.getInt("direccion_id"))
                .opcion_entrega_id(rs.getInt("opcion_entrega_id"))
                .estado(rs.getString("estado"))
                .numero_seguimiento(rs.getString("numero_seguimiento"))
                .fecha_creacion(rs.getString("fecha_creacion"))
                .fecha_actualizacion(rs.getString("fecha_actualizacion"))
                .build();
    }
}
