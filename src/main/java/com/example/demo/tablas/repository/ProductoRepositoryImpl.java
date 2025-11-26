package com.example.demo.tablas.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.demo.producto.model.Producto;
import com.example.demo.producto.repository.RMProducto;

@Repository
public class ProductoRepositoryImpl implements ProductoRepository {

    private static final Logger log = LoggerFactory.getLogger(ProductoRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /* CONSULTAS (QUERY) MYSQL */
    //---> CONSULTAS - PRODUCTO
    private final String SELECT_PRODUCTO_QUERY = "SELECT p.* FROM producto p";
    private final String UPDATE_PRODUCTO_QUERY = "UPDATE producto p";
    private final String DELETE_PRODUCTO_QUERY = "DELETE FROM producto p";

    @Autowired
    public ProductoRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = npJdbcTemplate;
    }

    /* REPOSITORIO - PRODUCTO */

    // GET
    @Override
    public List<Producto> getTotalidadProductos() {

        log.info("Obteniendo lista total de productos...");

        return this.jdbcTemplate.query(SELECT_PRODUCTO_QUERY, new RMProducto());
    }

    @Override
    public Producto getProductoPorId(Integer producto_id) {

        log.info("Obteniendo producto por ID: {}", producto_id);

        StringBuilder query = new StringBuilder(SELECT_PRODUCTO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("p.producto_id = :producto_id");
        params.addValue("producto_id", producto_id);

        return this.namedParameterJdbcTemplate.queryForObject(
                query.toString(),
                params,
                new RMProducto()
        );
    }

    @Override
    public List<Producto> getProductosPorCategoria(Integer categoria_id) {

        log.info("Obteniendo productos por categoria ID: {}", categoria_id);

        StringBuilder query = new StringBuilder(SELECT_PRODUCTO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("p.categoria_id = :categoria_id");
        params.addValue("categoria_id", categoria_id);

        return this.namedParameterJdbcTemplate.query(
                query.toString(),
                params,
                new RMProducto()
        );
    }

    // POST
    @Override
    public Integer createProducto(Producto p) {

        log.info("Creando producto: {}", p.getNombre());

        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(this.jdbcTemplate);

        List<String> columnas = new ArrayList<>();
        columnas.add("departamento_id");
        columnas.add("categoria_id");
        columnas.add("subcategoria_id");
        columnas.add("nombre");
        columnas.add("descripcion");
        columnas.add("precio");
        columnas.add("genero");
        columnas.add("stock_S");
        columnas.add("stock_M");
        columnas.add("stock_L");
        columnas.add("stock_XL");
        columnas.add("stock_XXL");
        columnas.add("stock_XXXL");
        columnas.add("stock");
        columnas.add("disponibilidad");
        columnas.add("codigo_sku");

        simpleInsert.setTableName("producto");
        simpleInsert.setColumnNames(columnas);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("departamento_id", p.getDepartamento_id());
        parametros.put("categoria_id", p.getCategoria_id());
        parametros.put("subcategoria_id", p.getSubcategoria_id());
        parametros.put("nombre", p.getNombre());
        parametros.put("descripcion", p.getDescripcion());
        parametros.put("precio", p.getPrecio());
        parametros.put("genero", p.getGenero());
        parametros.put("stock_S", p.getStock_S());
        parametros.put("stock_M", p.getStock_M());
        parametros.put("stock_L", p.getStock_L());
        parametros.put("stock_XL", p.getStock_XL());
        parametros.put("stock_XXL", p.getStock_XXL());
        parametros.put("stock", p.getStock());
        parametros.put("disponibilidad", p.getDisponibilidad());
        parametros.put("codigo_sku", p.getCodigo_sku());

        simpleInsert.setGeneratedKeyName("producto_id");

        Number producto_id = simpleInsert.executeAndReturnKey(parametros);

        return producto_id.intValue();
    }

    // PUT
    @Override
    public void updateProducto(Producto p) {

        log.info("Actualizando producto ID: {}", p.getProducto_id());

        StringBuilder query = new StringBuilder(UPDATE_PRODUCTO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" SET ");
        query.append("nombre = :nombre, ");
        params.addValue("nombre", p.getNombre());

        query.append("descripcion = :descripcion, ");
        params.addValue("descripcion", p.getDescripcion());

        query.append("precio = :precio, ");
        params.addValue("precio", p.getPrecio());

        query.append("genero = :genero, ");
        params.addValue("genero", p.getGenero());

        query.append("stock_S = :stock_S, ");
        params.addValue("stock_S", p.getStock_S());

        query.append("stock_M = :stock_M, ");
        params.addValue("stock_M", p.getStock_M());

        query.append("stock_L = :stock_L, ");
        params.addValue("stock_L", p.getStock_L());

        query.append("stock_XL = :stock_XL, ");
        params.addValue("stock_XL", p.getStock_XL());

        query.append("stock_XXL = :stock_XXL, ");
        params.addValue("stock_XXL", p.getStock_XXL());

        query.append("stock = :stock, ");
        params.addValue("stock", p.getStock());

        query.append("disponibilidad = :disponibilidad, ");
        params.addValue("disponibilidad", p.getDisponibilidad());

        query.append("codigo_sku = :codigo_sku ");
        params.addValue("codigo_sku", p.getCodigo_sku());

        query.append(" WHERE ");
        query.append("p.producto_id = :producto_id");
        params.addValue("producto_id", p.getProducto_id());

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }

    // DELETE
    @Override
    public void deleteProductoFisico(Integer producto_id) {

        log.info("Eliminando producto ID: {}", producto_id);

        StringBuilder query = new StringBuilder(DELETE_PRODUCTO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("p.producto_id = :producto_id");
        params.addValue("producto_id", producto_id);

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }
}
