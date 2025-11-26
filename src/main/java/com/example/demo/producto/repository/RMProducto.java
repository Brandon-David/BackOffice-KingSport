package com.example.demo.producto.repository;

import com.example.demo.producto.model.Producto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RMProducto implements RowMapper<Producto> {

	  @Override
	  public Producto mapRow(ResultSet rs, int rowNum) throws SQLException {

		  Producto producto = new Producto();

		  producto.setProducto_id(rs.getInt("producto_id"));
		  producto.setDepartamento_id(rs.getInt("departamento_id"));
	      producto.setCategoria_id(rs.getInt("categoria_id"));
	      producto.setSubcategoria_id(rs.getInt("subcategoria_id"));

	      producto.setNombre(rs.getString("nombre"));
	      producto.setDescripcion(rs.getString("descripcion"));
	      producto.setPrecio(rs.getDouble("precio"));
	      producto.setGenero(rs.getString("genero"));

	      producto.setStock_XS(rs.getInt("stock_XS"));
	      producto.setStock_S(rs.getInt("stock_S"));
	      producto.setStock_M(rs.getInt("stock_M"));
	      producto.setStock_L(rs.getInt("stock_L"));
	      producto.setStock_XL(rs.getInt("stock_XL"));
	      producto.setStock_XXL(rs.getInt("stock_XXL"));

	      producto.setStock(rs.getInt("stock"));
	      producto.setDisponibilidad(rs.getBoolean("disponibilidad"));

	      producto.setCodigo_sku(rs.getString("codigo_sku"));
	      producto.setModelo(rs.getString("modelo"));
	      producto.setSerie(rs.getString("serie"));
	      producto.setMaterial(rs.getString("material"));
	      producto.setTipo_tela(rs.getString("tipo_tela"));
	      producto.setEstilo(rs.getString("estilo"));
	      producto.setDimensiones(rs.getString("dimensiones"));

	      producto.setFecha_creacion(rs.getString("fecha_creacion"));
	      producto.setFecha_actualizacion(rs.getString("fecha_actualizacion"));

	      return producto;
	  }

	  public Producto mapProducto(ResultSet rs, int rowNum, String prefix) throws SQLException {

		  Producto producto = new Producto();

		  producto.setProducto_id(rs.getInt("producto_id"));
	      producto.setDepartamento_id(rs.getInt("departamento_id"));
	      producto.setCategoria_id(rs.getInt("categoria_id"));
	      producto.setSubcategoria_id(rs.getInt("subcategoria_id"));

	      producto.setNombre(rs.getString("nombre"));
	      producto.setDescripcion(rs.getString("descripcion"));
	      producto.setPrecio(rs.getDouble("precio"));
	      producto.setGenero(rs.getString("genero"));

	      producto.setStock_XS(rs.getInt("stock_XS"));
	      producto.setStock_S(rs.getInt("stock_S"));
	      producto.setStock_M(rs.getInt("stock_M"));
	      producto.setStock_L(rs.getInt("stock_L"));
	      producto.setStock_XL(rs.getInt("stock_XL"));
	      producto.setStock_XXL(rs.getInt("stock_XXL"));

	      producto.setStock(rs.getInt("stock"));
	      producto.setDisponibilidad(rs.getBoolean("disponibilidad"));

	      producto.setCodigo_sku(rs.getString("codigo_sku"));
	      producto.setModelo(rs.getString("modelo"));
	      producto.setSerie(rs.getString("serie"));
	      producto.setMaterial(rs.getString("material"));
	      producto.setTipo_tela(rs.getString("tipo_tela"));
	      producto.setEstilo(rs.getString("estilo"));
	      producto.setDimensiones(rs.getString("dimensiones"));

	      producto.setFecha_creacion(rs.getString(prefix + "fecha_creacion"));
	      producto.setFecha_actualizacion(rs.getString(prefix + "fecha_actualizacion"));

	      return producto;

	  }
	    
}
