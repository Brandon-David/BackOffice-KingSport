package com.example.demo.diseño.repository;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.diseño.model.Diseño;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RMDiseño implements RowMapper<Diseño> {

    @Override
    public Diseño mapRow(ResultSet rs, int rowNum) throws SQLException {

        Diseño diseno = new Diseño(); // Instancia de la entidad Diseño

        diseno.setDiseno_id(rs.getInt("diseno_id"));              // Mapeas los valores que obtienes de la BD
        diseno.setCliente_id(rs.getInt("cliente_id"));            // con los atributos de la clase Diseño
        diseno.setNombre_equipo(rs.getString("nombre_equipo"));
        diseno.setPosicion(rs.getString("posicion"));
        diseno.setNumeracion(rs.getString("numeracion"));
        diseno.setTipo_cuello(rs.getString("tipo_cuello"));
        diseno.setDescripcion_idea(rs.getString("descripcion_idea"));
        diseno.setBoceto(rs.getString("boceto"));
        diseno.setEstado(rs.getString("estado"));
        diseno.setFecha_creacion(rs.getString("fecha_creacion"));
        diseno.setFecha_actualizacion(rs.getString("fecha_actualizacion"));

        return diseno;
    }

    
}
