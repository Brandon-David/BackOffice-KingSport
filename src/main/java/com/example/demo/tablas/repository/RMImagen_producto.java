package com.example.demo.tablas.repository;

import com.example.demo.tablas.model.Imagen_producto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RMImagen_producto implements RowMapper<Imagen_producto> {

    @Override
    public Imagen_producto mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Imagen_producto.builder()
                .imagen_producto_id(rs.getInt("imagen_producto_id"))
                .producto_id(rs.getInt("producto_id"))
                .url(rs.getString("url"))
                .orden(rs.getInt("orden"))
                .es_principal(rs.getBoolean("es_principal"))
                .fecha_creacion(rs.getString("fecha_creacion"))
                .fecha_actualizacion(rs.getString("fecha_actualizacion"))
                .build();
    }
}
