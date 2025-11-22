package com.example.demo.tablas.repository;

import com.example.demo.tablas.model.Subcategoria;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RMSubcategoria implements RowMapper<Subcategoria> {

    @Override
    public Subcategoria mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Subcategoria.builder()
                .subcategoria_id(rs.getInt("subcategoria_id"))
                .nombre(rs.getString("nombre"))
                .fecha_creacion(rs.getString("fecha_creacion"))
                .fecha_actualizacion(rs.getString("fecha_actualizacion"))
                .build();
    }
}
