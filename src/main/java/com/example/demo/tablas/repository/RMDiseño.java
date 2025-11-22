package com.example.demo.tablas.repository;

import com.example.demo.tablas.model.Diseño;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RMDiseño implements RowMapper<Diseño> {

    @Override
    public Diseño mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Diseño.builder()
                .diseno_id(rs.getInt("diseno_id"))
                .cliente_id(rs.getInt("cliente_id"))
                .nombre_equipo(rs.getString("nombre_equipo"))
                .posicion(rs.getString("posicion"))
                .numeracion(rs.getString("numeracion"))
                .tipo_cuello(rs.getString("tipo_cuello"))
                .descripcion_idea(rs.getString("descripcion_idea"))
                .boceto(rs.getString("boceto"))
                .estado(rs.getString("estado"))
                .fecha_creacion(rs.getString("fecha_creacion"))
                .fecha_actualizacion(rs.getString("fecha_actualizacion"))
                .build();
    }
}
