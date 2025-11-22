package com.example.demo.tablas.repository;

import com.example.demo.tablas.model.Producto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RMProducto implements RowMapper<Producto> {

    @Override
    public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Producto.builder()
                .producto_id(rs.getInt("producto_id"))
                .departamento_id(rs.getInt("departamento_id"))
                .categoria_id(rs.getInt("categoria_id"))
                .subcategoria_id(rs.getInt("subcategoria_id"))
                .nombre(rs.getString("nombre"))
                .descripcion(rs.getString("descripcion"))
                .precio(rs.getDouble("precio"))
                .genero(rs.getString("genero"))
                .stock_S(rs.getInt("stock_S"))
                .stock_M(rs.getInt("stock_M"))
                .stock_L(rs.getInt("stock_L"))
                .stock_XL(rs.getInt("stock_XL"))
                .stock_XXL(rs.getInt("stock_XXL"))
                .stock_XXXL(rs.getInt("stock_XXXL"))
                .stock(rs.getInt("stock"))
                .disponibilidad(rs.getBoolean("disponibilidad"))
                .codigo_sku(rs.getString("codigo_sku"))
                .fecha_creacion(rs.getString("fecha_creacion"))
                .fecha_actualizacion(rs.getString("fecha_actualizacion"))
                .build();
    }
}
