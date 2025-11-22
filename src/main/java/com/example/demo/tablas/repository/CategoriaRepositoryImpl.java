package com.example.demo.tablas.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.tablas.model.Categoria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriaRepositoryImpl implements CategoriaRepository {

    private static final Logger log = LoggerFactory.getLogger(CategoriaRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /* CONSULTAS (QUERY) MYSQL */
    private final String SELECT_CAT_QUERY = "SELECT c.* FROM categoria c";
    private final String UPDATE_CAT_QUERY = "UPDATE categoria c";
    private final String DELETE_CAT_QUERY = "DELETE FROM categoria c";

    @Autowired
    public CategoriaRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = npJdbcTemplate;
    }

    /* REPOSITORIO - CATEGORIA */

    @Override
    public List<Categoria> getTotalidadCategorias() {

        log.info("Obteniendo lista total de categorias...");

        return this.jdbcTemplate.query(SELECT_CAT_QUERY, new RMCategoria());
    }

    @Override
    public Categoria getCategoriaPorId(Integer categoria_id) {

        log.info("Obteniendo categoria ID: {}", categoria_id);

        StringBuilder query = new StringBuilder(SELECT_CAT_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE c.categoria_id = :categoria_id");
        params.addValue("categoria_id", categoria_id);

        return this.namedParameterJdbcTemplate.queryForObject(
                query.toString(),
                params,
                new RMCategoria()
        );
    }

    @Override
    public Integer createCategoria(Categoria c) {

        log.info("Creando categoria: {}", c.getNombre());

        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(this.jdbcTemplate);

        List<String> columnas = new ArrayList<>();
        columnas.add("nombre");

        simpleInsert.setTableName("categoria");
        simpleInsert.setColumnNames(columnas);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nombre", c.getNombre());

        simpleInsert.setGeneratedKeyName("categoria_id");

        Number categoria_id = simpleInsert.executeAndReturnKey(parametros);

        return categoria_id.intValue();
    }

    @Override
    public void updateCategoria(Categoria c) {

        log.info("Actualizando categoria ID: {}", c.getCategoria_id());

        StringBuilder query = new StringBuilder(UPDATE_CAT_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" SET nombre = :nombre ");
        params.addValue("nombre", c.getNombre());

        query.append(" WHERE c.categoria_id = :categoria_id");
        params.addValue("categoria_id", c.getCategoria_id());

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }

    @Override
    public void deleteCategoriaFisico(Integer categoria_id) {

        log.info("Eliminando categoria ID: {}", categoria_id);

        StringBuilder query = new StringBuilder(DELETE_CAT_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE c.categoria_id = :categoria_id");
        params.addValue("categoria_id", categoria_id);

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }
}
