package com.example.demo.departamento.repository;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.departamento.model.Categoria;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RMCategoria implements RowMapper<Categoria> {

    @Override
    public Categoria mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        Categoria categoria = new Categoria();
        
        categoria.setCategoria_id(rs.getInt("categoria_id"));
        categoria.setDepartamento_id(rs.getInt("departamento_id"));
        categoria.setNombre(rs.getString("nombre"));
        categoria.setFecha_creacion(rs.getString("fecha_creacion"));
        categoria.setFecha_actualizacion(rs.getString("fecha_actualizacion"));
        
        return categoria;
    }
    
    public Categoria mapCategoria(ResultSet rs, int rowNum, String prefix) throws SQLException {
        
        Categoria categoria = new Categoria();
        
        categoria.setCategoria_id(rs.getInt("categoria_id"));
        categoria.setDepartamento_id(rs.getInt(prefix + "departamento_id"));
        categoria.setNombre(rs.getString(prefix +"nombre"));
        categoria.setFecha_creacion(rs.getString(prefix + "fecha_creacion"));
        categoria.setFecha_actualizacion(rs.getString(prefix + "fecha_actualizacion"));
        
        return categoria;
    }
}
