package com.example.demo.departamento.repository;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.departamento.model.Departamento;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RMDepartamento implements RowMapper<Departamento> {

    @Override
    public Departamento mapRow(ResultSet rs, int rowNum) throws SQLException {

        Departamento departamento = new Departamento();

        departamento.setDepartamento_id(rs.getInt("departamento_id"));
        departamento.setNombre(rs.getString("nombre"));
        departamento.setFecha_creacion(rs.getString("fecha_creacion"));
        departamento.setFecha_actualizacion(rs.getString("fecha_actualizacion"));

        return departamento;
    }

    public Departamento mapDepartamento(ResultSet rs, int rowNum, String prefix) throws SQLException {

        Departamento departamento = new Departamento();

        departamento.setDepartamento_id(rs.getInt("departamento_id"));
        departamento.setNombre(rs.getString(prefix + "nombre"));
        departamento.setFecha_creacion(rs.getString(prefix + "fecha_creacion"));
        departamento.setFecha_actualizacion(rs.getString(prefix + "fecha_actualizacion"));

        return departamento;
    }
}

