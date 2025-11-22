package com.example.demo.tablas.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.tablas.model.Subcategoria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class SubcategoriaRepositoryImpl implements SubcategoriaRepository {

    private static final Logger log = LoggerFactory.getLogger(SubcategoriaRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /* CONSULTAS (QUERY) MYSQL */
    private final String SELECT_SUBCAT_QUERY = "SELECT s.* FROM subcategoria s";
    private final String UPDATE_SUBCAT_QUERY = "UPDATE subcategoria s";
    private final String DELETE_SUBCAT_QUERY = "DELETE FROM subcategoria s";

    @Autowired
    public SubcategoriaRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = npJdbcTemplate;
    }

    /* REPOSITORIO - SUBCATEGORIA */

    @Override
    public List<Subcategoria> getTotalidadSubcategorias() {

        log.info("Obteniendo lista total de subcategorias...");

        return this.jdbcTemplate.query(SELECT_SUBCAT_QUERY, new RMSubcategoria());
    }

    @Override
    public Subcategoria getSubcategoriaPorId(Integer subcategoria_id) {

        log.info("Obteniendo subcategoria ID: {}", subcategoria_id);

        StringBuilder query = new StringBuilder(SELECT_SUBCAT_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE s.subcategoria_id = :subcategoria_id");
        params.addValue("subcategoria_id", subcategoria_id);

        return this.namedParameterJdbcTemplate.queryForObject(
                query.toString(),
                params,
                new RMSubcategoria()
        );
    }

    @Override
    public Integer createSubcategoria(Subcategoria s) {

        log.info("Creando subcategoria: {}", s.getNombre());

        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(this.jdbcTemplate);

        List<String> columnas = new ArrayList<>();
        columnas.add("nombre");

        simpleInsert.setTableName("subcategoria");
        simpleInsert.setColumnNames(columnas);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nombre", s.getNombre());

        simpleInsert.setGeneratedKeyName("subcategoria_id");

        Number subcategoria_id = simpleInsert.executeAndReturnKey(parametros);

        return subcategoria_id.intValue();
    }

    @Override
    public void updateSubcategoria(Subcategoria s) {

        log.info("Actualizando subcategoria ID: {}", s.getSubcategoria_id());

        StringBuilder query = new StringBuilder(UPDATE_SUBCAT_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" SET nombre = :nombre ");
        params.addValue("nombre", s.getNombre());

        query.append(" WHERE s.subcategoria_id = :subcategoria_id");
        params.addValue("subcategoria_id", s.getSubcategoria_id());

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }

    @Override
    public void deleteSubcategoriaFisico(Integer subcategoria_id) {

        log.info("Eliminando subcategoria ID: {}", subcategoria_id);

        StringBuilder query = new StringBuilder(DELETE_SUBCAT_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE s.subcategoria_id = :subcategoria_id");
        params.addValue("subcategoria_id", subcategoria_id);

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }
}
