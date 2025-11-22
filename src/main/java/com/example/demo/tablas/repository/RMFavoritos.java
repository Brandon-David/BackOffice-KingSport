package com.example.demo.tablas.repository;

import com.example.demo.tablas.model.Favoritos;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RMFavoritos implements RowMapper<Favoritos> {

    @Override
    public Favoritos mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Favoritos.builder()
                .favoritos_id(rs.getInt("favoritos_id"))
                .cliente_id(rs.getInt("cliente_id"))
                .producto_id(rs.getInt("producto_id"))
                .fecha_creacion(rs.getString("fecha_creacion"))
                .fecha_actualizacion(rs.getString("fecha_actualizacion"))
                .build();
    }
}
