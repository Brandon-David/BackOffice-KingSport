package com.example.demo.tablas.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.tablas.model.Departamento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class DepartamentoRepositoryImpl implements DepartamentoRepository {

    private static final Logger log = LoggerFactory.getLogger(DepartamentoRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /* CONSULTAS (QUERY) MYSQL */
    private final String SELECT_DEPTO_QUERY = "SELECT d.* FROM departamento d";
    private final String UPDATE_DEPTO_QUERY = "UPDATE departamento d";
    private final String DELETE_DEPTO_QUERY = "DELETE FROM departamento d";

    @Autowired
    public DepartamentoRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = npJdbcTemplate;
    }

    /* REPOSITORIO - DEPARTAMENTO */

    @Override
    public List<Departamento> getTotalidadDepartamentos() {

        log.info("Obteniendo lista total de departamentos...");

        return this.jdbcTemplate.query(SELECT_DEPTO_QUERY, new RMDepartamento());
    }

    @Override
    public Departamento getDepartamentoPorId(Integer departamento_id) {

        log.info("Obteniendo departamento ID: {}", departamento_id);

        StringBuilder query = new StringBuilder(SELECT_DEPTO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE d.departamento_id = :departamento_id");
        params.addValue("departamento_id", departamento_id);

        return this.namedParameterJdbcTemplate.queryForObject(
                query.toString(),
                params,
                new RMDepartamento()
        );
    }

    @Override
    public Integer createDepartamento(Departamento d) {

        log.info("Creando departamento: {}", d.getNombre());

        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(this.jdbcTemplate);

        List<String> columnas = new ArrayList<>();
        columnas.add("nombre");

        simpleInsert.setTableName("departamento");
        simpleInsert.setColumnNames(columnas);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nombre", d.getNombre());

        simpleInsert.setGeneratedKeyName("departamento_id");

        Number departamento_id = simpleInsert.executeAndReturnKey(parametros);

        return departamento_id.intValue();
    }

    @Override
    public void updateDepartamento(Departamento d) {

        log.info("Actualizando departamento ID: {}", d.getDepartamento_id());

        StringBuilder query = new StringBuilder(UPDATE_DEPTO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" SET nombre = :nombre ");
        params.addValue("nombre", d.getNombre());

        query.append(" WHERE d.departamento_id = :departamento_id");
        params.addValue("departamento_id", d.getDepartamento_id());

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }

    @Override
    public void deleteDepartamentoFisico(Integer departamento_id) {

        log.info("Eliminando departamento ID: {}", departamento_id);

        StringBuilder query = new StringBuilder(DELETE_DEPTO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE d.departamento_id = :departamento_id");
        params.addValue("departamento_id", departamento_id);

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }
}
