package com.example.demo.tablas.repository;

import com.example.demo.tablas.model.Opcion_entrega;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RMOpcion_entrega implements RowMapper<Opcion_entrega> {

    @Override
    public Opcion_entrega mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Opcion_entrega.builder()
                .opcion_entrega_id(rs.getInt("opcion_entrega_id"))
                .nombre(rs.getString("nombre"))
                .precio(rs.getDouble("precio"))
                .tiempo_estimado(rs.getString("tiempo_estimado"))
                .tipo(rs.getString("tipo"))
                .estado(rs.getString("estado"))
                .fecha_creacion(rs.getString("fecha_creacion"))
                .fecha_actualizacion(rs.getString("fecha_actualizacion"))
                .build();
    }
}
