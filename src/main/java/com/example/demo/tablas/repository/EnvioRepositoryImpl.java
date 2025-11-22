package com.example.demo.tablas.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.tablas.model.Envio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class EnvioRepositoryImpl implements EnvioRepository {

    private static final Logger log = LoggerFactory.getLogger(EnvioRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /* CONSULTAS (QUERY) MYSQL */
    private final String SELECT_ENVIO_QUERY = "SELECT e.* FROM envio e";
    private final String UPDATE_ENVIO_QUERY = "UPDATE envio e";
    private final String DELETE_ENVIO_QUERY = "DELETE FROM envio e";

    @Autowired
    public EnvioRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = npJdbcTemplate;
    }

    /* REPOSITORIO - ENVIO */

    @Override
    public List<Envio> getTotalidadEnvios() {

        log.info("Obteniendo lista total de envios...");

        return this.jdbcTemplate.query(SELECT_ENVIO_QUERY, new RMEnvio());
    }

    @Override
    public Envio getEnvioPorId(Integer envio_id) {

        log.info("Obteniendo envio ID: {}", envio_id);

        StringBuilder query = new StringBuilder(SELECT_ENVIO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE e.envio_id = :envio_id");
        params.addValue("envio_id", envio_id);

        return this.namedParameterJdbcTemplate.queryForObject(
                query.toString(),
                params,
                new RMEnvio()
        );
    }

    @Override
    public List<Envio> getEnviosPorPedido(Integer pedido_id) {

        log.info("Obteniendo envios del pedido ID: {}", pedido_id);

        StringBuilder query = new StringBuilder(SELECT_ENVIO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE e.pedido_id = :pedido_id");
        params.addValue("pedido_id", pedido_id);

        return this.namedParameterJdbcTemplate.query(
                query.toString(),
                params,
                new RMEnvio()
        );
    }

    @Override
    public Integer createEnvio(Envio e) {

        log.info("Creando envio para pedido ID: {}", e.getPedido_id());

        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(this.jdbcTemplate);

        List<String> columnas = new ArrayList<>();
        columnas.add("pedido_id");
        columnas.add("direccion_id");
        columnas.add("opcion_entrega_id");
        columnas.add("estado");
        columnas.add("numero_seguimiento");

        simpleInsert.setTableName("envio");
        simpleInsert.setColumnNames(columnas);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("pedido_id", e.getPedido_id());
        parametros.put("direccion_id", e.getDireccion_id());
        parametros.put("opcion_entrega_id", e.getOpcion_entrega_id());
        parametros.put("estado", e.getEstado());
        parametros.put("numero_seguimiento", e.getNumero_seguimiento());

        simpleInsert.setGeneratedKeyName("envio_id");

        Number envio_id = simpleInsert.executeAndReturnKey(parametros);

        return envio_id.intValue();
    }

    @Override
    public void updateEnvio(Envio e) {

        log.info("Actualizando envio ID: {}", e.getEnvio_id());

        StringBuilder query = new StringBuilder(UPDATE_ENVIO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" SET ");
        query.append("direccion_id = :direccion_id, ");
        params.addValue("direccion_id", e.getDireccion_id());

        query.append("opcion_entrega_id = :opcion_entrega_id, ");
        params.addValue("opcion_entrega_id", e.getOpcion_entrega_id());

        query.append("estado = :estado, ");
        params.addValue("estado", e.getEstado());

        query.append("numero_seguimiento = :numero_seguimiento ");
        params.addValue("numero_seguimiento", e.getNumero_seguimiento());

        query.append(" WHERE e.envio_id = :envio_id");
        params.addValue("envio_id", e.getEnvio_id());

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }

    @Override
    public void deleteEnvioFisico(Integer envio_id) {

        log.info("Eliminando envio ID: {}", envio_id);

        StringBuilder query = new StringBuilder(DELETE_ENVIO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE e.envio_id = :envio_id");
        params.addValue("envio_id", envio_id);

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }
}
