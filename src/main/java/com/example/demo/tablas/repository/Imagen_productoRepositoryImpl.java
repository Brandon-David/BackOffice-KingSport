package com.example.demo.tablas.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.tablas.model.Imagen_producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class Imagen_productoRepositoryImpl implements Imagen_productoRepository {

    private static final Logger log = LoggerFactory.getLogger(Imagen_productoRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /* CONSULTAS (QUERY) MYSQL */
    private final String SELECT_IMG_QUERY = "SELECT i.* FROM imagen_producto i";
    private final String UPDATE_IMG_QUERY = "UPDATE imagen_producto i";
    private final String DELETE_IMG_QUERY = "DELETE FROM imagen_producto i";

    @Autowired
    public Imagen_productoRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = npJdbcTemplate;
    }

    /* REPOSITORIO - IMAGEN_PRODUCTO */

    @Override
    public List<Imagen_producto> getTotalidadImagenes() {

        log.info("Obteniendo lista total de imagen_producto...");

        return this.jdbcTemplate.query(SELECT_IMG_QUERY, new RMImagen_producto());
    }

    @Override
    public Imagen_producto getImagenPorId(Integer imagen_producto_id) {

        log.info("Obteniendo imagen_producto ID: {}", imagen_producto_id);

        StringBuilder query = new StringBuilder(SELECT_IMG_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE i.imagen_producto_id = :imagen_producto_id");
        params.addValue("imagen_producto_id", imagen_producto_id);

        return this.namedParameterJdbcTemplate.queryForObject(
                query.toString(),
                params,
                new RMImagen_producto()
        );
    }

    @Override
    public List<Imagen_producto> getImagenesPorProducto(Integer producto_id) {

        log.info("Obteniendo imágenes del producto ID: {}", producto_id);

        StringBuilder query = new StringBuilder(SELECT_IMG_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE i.producto_id = :producto_id");
        params.addValue("producto_id", producto_id);

        return this.namedParameterJdbcTemplate.query(
                query.toString(),
                params,
                new RMImagen_producto()
        );
    }

    @Override
    public Integer createImagen(Imagen_producto img) {

        log.info("Creando imagen_producto para producto ID: {}", img.getProducto_id());

        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(this.jdbcTemplate);

        List<String> columnas = new ArrayList<>();
        columnas.add("producto_id");
        columnas.add("url");
        columnas.add("orden");
        columnas.add("es_principal");

        simpleInsert.setTableName("imagen_producto");
        simpleInsert.setColumnNames(columnas);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("producto_id", img.getProducto_id());
        parametros.put("url", img.getUrl());
        parametros.put("orden", img.getOrden());
        parametros.put("es_principal", img.getEs_principal());

        simpleInsert.setGeneratedKeyName("imagen_producto_id");

        Number imagen_producto_id = simpleInsert.executeAndReturnKey(parametros);

        return imagen_producto_id.intValue();
    }

    @Override
    public void updateImagen(Imagen_producto img) {

        log.info("Actualizando imagen_producto ID: {}", img.getImagen_producto_id());

        StringBuilder query = new StringBuilder(UPDATE_IMG_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" SET ");
        query.append("url = :url, ");
        params.addValue("url", img.getUrl());

        query.append("orden = :orden, ");
        params.addValue("orden", img.getOrden());

        query.append("es_principal = :es_principal ");
        params.addValue("es_principal", img.getEs_principal());

        query.append(" WHERE i.imagen_producto_id = :imagen_producto_id");
        params.addValue("imagen_producto_id", img.getImagen_producto_id());

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }

    @Override
    public void deleteImagenFisica(Integer imagen_producto_id) {

        log.info("Eliminando imagen_producto ID: {}", imagen_producto_id);

        StringBuilder query = new StringBuilder(DELETE_IMG_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE i.imagen_producto_id = :imagen_producto_id");
        params.addValue("imagen_producto_id", imagen_producto_id);

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }
}
