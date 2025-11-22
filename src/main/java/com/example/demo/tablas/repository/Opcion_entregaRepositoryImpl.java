package com.example.demo.tablas.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.tablas.model.Opcion_entrega;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class Opcion_entregaRepositoryImpl implements Opcion_entregaRepository {

    private static final Logger log = LoggerFactory.getLogger(Opcion_entregaRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /* CONSULTAS (QUERY) MYSQL */
    private final String SELECT_OPCION_QUERY = "SELECT oe.* FROM opcion_entrega oe";
    private final String UPDATE_OPCION_QUERY = "UPDATE opcion_entrega oe";
    private final String DELETE_OPCION_QUERY = "DELETE FROM opcion_entrega oe";

    @Autowired
    public Opcion_entregaRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = npJdbcTemplate;
    }

    /* REPOSITORIO - OPCION_ENTREGA */

    @Override
    public List<Opcion_entrega> getTotalidadOpcionesEntrega() {

        log.info("Obteniendo lista total de opciones de entrega...");

        return this.jdbcTemplate.query(SELECT_OPCION_QUERY, new RMOpcion_entrega());
    }

    @Override
    public Opcion_entrega getOpcionEntregaPorId(Integer opcion_entrega_id) {

        log.info("Obteniendo opcion_entrega ID: {}", opcion_entrega_id);

        StringBuilder query = new StringBuilder(SELECT_OPCION_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE oe.opcion_entrega_id = :opcion_entrega_id");
        params.addValue("opcion_entrega_id", opcion_entrega_id);

        return this.namedParameterJdbcTemplate.queryForObject(
                query.toString(),
                params,
                new RMOpcion_entrega()
        );
    }

    @Override
    public Integer createOpcionEntrega(Opcion_entrega o) {

        log.info("Creando opcion_entrega: {}", o.getNombre());

        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(this.jdbcTemplate);

        List<String> columnas = new ArrayList<>();
        columnas.add("nombre");
        columnas.add("precio");
        columnas.add("tiempo_estimado");
        columnas.add("tipo");
        columnas.add("estado");

        simpleInsert.setTableName("opcion_entrega");
        simpleInsert.setColumnNames(columnas);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nombre", o.getNombre());
        parametros.put("precio", o.getPrecio());
        parametros.put("tiempo_estimado", o.getTiempo_estimado());
        parametros.put("tipo", o.getTipo());
        parametros.put("estado", o.getEstado());

        simpleInsert.setGeneratedKeyName("opcion_entrega_id");

        Number opcion_entrega_id = simpleInsert.executeAndReturnKey(parametros);

        return opcion_entrega_id.intValue();
    }

    @Override
    public void updateOpcionEntrega(Opcion_entrega o) {

        log.info("Actualizando opcion_entrega ID: {}", o.getOpcion_entrega_id());

        StringBuilder query = new StringBuilder(UPDATE_OPCION_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" SET ");
        query.append("nombre = :nombre, ");
        params.addValue("nombre", o.getNombre());

        query.append("precio = :precio, ");
        params.addValue("precio", o.getPrecio());

        query.append("tiempo_estimado = :tiempo_estimado, ");
        params.addValue("tiempo_estimado", o.getTiempo_estimado());

        query.append("tipo = :tipo, ");
        params.addValue("tipo", o.getTipo());

        query.append("estado = :estado ");
        params.addValue("estado", o.getEstado());

        query.append(" WHERE oe.opcion_entrega_id = :opcion_entrega_id");
        params.addValue("opcion_entrega_id", o.getOpcion_entrega_id());

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }

    @Override
    public void deleteOpcionEntregaFisica(Integer opcion_entrega_id) {

        log.info("Eliminando opcion_entrega ID: {}", opcion_entrega_id);

        StringBuilder query = new StringBuilder(DELETE_OPCION_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE oe.opcion_entrega_id = :opcion_entrega_id");
        params.addValue("opcion_entrega_id", opcion_entrega_id);

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }
}
