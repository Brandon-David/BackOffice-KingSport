package com.example.demo.cliente.repository;


import org.springframework.jdbc.core.RowMapper;
import com.example.demo.cliente.model.Favoritos;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RMFavoritos implements RowMapper<Favoritos> {

    @Override
    public Favoritos mapRow(ResultSet rs, int rowNum) throws SQLException {

        Favoritos favoritos = new Favoritos();

        favoritos.setFavoritos_id(rs.getInt("favoritos_id"));
        favoritos.setCliente_id(rs.getInt("cliente_id"));
        favoritos.setProducto_id(rs.getInt("producto_id"));
        favoritos.setFecha_creacion(rs.getString("fecha_creacion"));
        favoritos.setFecha_actualizacion(rs.getString("fecha_actualizacion"));

        return favoritos;
    }
}
