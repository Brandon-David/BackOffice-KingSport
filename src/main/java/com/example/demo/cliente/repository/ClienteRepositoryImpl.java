package com.example.demo.cliente.repository;

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

import com.example.demo.cliente.model.Cliente;

@Repository
public class ClienteRepositoryImpl implements ClienteRepository {

    private static final Logger log = LoggerFactory.getLogger(ClienteRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /* CONSULTAS (QUERY) MYSQL */
    //---> CONSULTAS - CLIENTE
    private final String SELECT_CLIENTE_QUERY = "SELECT c.* FROM cliente c";
    private final String UPDATE_CLIENTE_QUERY = "UPDATE cliente c";
    private final String DELETE_CLIENTE_QUERY = "DELETE FROM cliente c";

    @Autowired
    public ClienteRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = npJdbcTemplate;
    }

    /* REPOSITORIO - CLIENTE */

    @Override
    public List<Cliente> getTotalidadClientes() {

        log.info("Obteniendo lista total de clientes...");

        return this.jdbcTemplate.query(SELECT_CLIENTE_QUERY, new RMCliente());
    }

    @Override
    public Cliente getCliente(Integer cliente_id) {

        log.info("Obteniendo cliente por ID: {}", cliente_id);

        StringBuilder query = new StringBuilder(SELECT_CLIENTE_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("c.cliente_id = :cliente_id");
        params.addValue("cliente_id", cliente_id);

        return this.namedParameterJdbcTemplate.queryForObject(
                query.toString(),
                params,
                new RMCliente()
        );
    }

    @Override
    public Integer createCliente(Cliente c) {

        log.info("Creando cliente nuevo...");

        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(this.jdbcTemplate);

        List<String> columnas = new ArrayList<>();
        columnas.add("direccion_principal_id");
        columnas.add("nombre_completo");
        columnas.add("correo");
        columnas.add("contrasena");

        simpleInsert.setTableName("cliente");
        simpleInsert.setColumnNames(columnas);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("direccion_principal_id", c.getDireccion_principal_id());
        parametros.put("nombre_completo", c.getNombre_completo());
        parametros.put("correo", c.getCorreo());
        parametros.put("contrasena", c.getContrasena());

        simpleInsert.setGeneratedKeyName("cliente_id");

        Number cliente_id = simpleInsert.executeAndReturnKey(parametros);

        return cliente_id.intValue();
    }

    @Override
    public void updateCliente(Cliente c) {

        log.info("Actualizando cliente ID: {}", c.getCliente_id());

        StringBuilder query = new StringBuilder(UPDATE_CLIENTE_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" SET ");
        query.append("direccion_principal_id = :direccion_principal_id, ");
        params.addValue("direccion_principal_id", c.getDireccion_principal_id());

        query.append("nombre_completo = :nombre_completo, ");
        params.addValue("nombre_completo", c.getNombre_completo());

        query.append("correo = :correo ");
        params.addValue("correo", c.getCorreo());

        query.append(" WHERE ");
        query.append("c.cliente_id = :cliente_id");
        params.addValue("cliente_id", c.getCliente_id());

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }

    @Override
    public void deleteClienteFisico(Integer cliente_id) {

        log.info("Eliminando cliente ID: {}", cliente_id);

        StringBuilder query = new StringBuilder(DELETE_CLIENTE_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("c.cliente_id = :cliente_id");
        params.addValue("cliente_id", cliente_id);

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }
}
