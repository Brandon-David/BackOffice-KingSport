package com.example.demo.tablas.repository;

import com.example.demo.tablas.model.Departamento;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RMDepartamento implements RowMapper<Departamento> {

    @Override
    public Departamento mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Departamento.builder()
                .departamento_id(rs.getInt("departamento_id"))
                .nombre(rs.getString("nombre"))
                .fecha_creacion(rs.getString("fecha_creacion"))
                .fecha_actualizacion(rs.getString("fecha_actualizacion"))
                .build();
    }
}
