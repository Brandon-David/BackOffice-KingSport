package com.example.demo.departamento.repository;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.departamento.model.Subcategoria;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RMSubcategoria implements RowMapper<Subcategoria> {

    @Override
    public Subcategoria mapRow(ResultSet rs, int rowNum) throws SQLException {

        Subcategoria subcategoria = new Subcategoria();

        subcategoria.setSubcategoria_id(rs.getInt("subcategoria_id"));
        subcategoria.setNombre(rs.getString("nombre"));
        subcategoria.setFecha_creacion(rs.getString("fecha_creacion"));
        subcategoria.setFecha_actualizacion(rs.getString("fecha_actualizacion"));

        return subcategoria;
    }

    public Subcategoria mapSubcategoria(ResultSet rs, int rowNum, String prefix) throws SQLException {

        Subcategoria subcategoria = new Subcategoria();

        subcategoria.setSubcategoria_id(rs.getInt("subcategoria_id"));
        subcategoria.setNombre(rs.getString("nombre"));
        subcategoria.setFecha_creacion(rs.getString(prefix + "fecha_creacion"));
        subcategoria.setFecha_actualizacion(rs.getString(prefix + "fecha_actualizacion"));

        return subcategoria;
    }
}

