package com.example.demo.tablas.repository;

import com.example.demo.tablas.model.Categoria;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RMCategoria implements RowMapper<Categoria> {

    @Override
    public Categoria mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Categoria.builder()
                .categoria_id(rs.getInt("categoria_id"))
                .nombre(rs.getString("nombre"))
                .fecha_creacion(rs.getString("fecha_creacion"))
                .fecha_actualizacion(rs.getString("fecha_actualizacion"))
                .build();
    }
}
