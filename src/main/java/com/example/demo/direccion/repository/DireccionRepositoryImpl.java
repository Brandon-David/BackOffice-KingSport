package com.example.demo.direccion.repository;

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

import com.example.demo.direccion.model.Direccion;

@Repository
public class DireccionRepositoryImpl implements DireccionRepository {

    private static final Logger log = LoggerFactory.getLogger(DireccionRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /* CONSULTAS (QUERY) MYSQL */
    //---> CONSULTAS - DIRECCION
    private final String SELECT_DIRECCION_QUERY = "SELECT d.* FROM direccion d";

    private final String UPDATE_DIRECCION_QUERY = "UPDATE direccion d";

    private final String DELETE_DIRECCION_QUERY = "DELETE FROM direccion d";

    @Autowired
    public DireccionRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = npJdbcTemplate;
    }

    /* REPOSITORIO - DIRECCION */

    // GET
    @Override
    public List<Direccion> getTotalidadDirecciones() {

        log.info("Obteniendo lista total de direcciones...");

        return this.jdbcTemplate.query(SELECT_DIRECCION_QUERY, new RMDireccion());
    }

    @Override
    public Direccion getDireccion(Integer direccion_id) {

        log.info("Obteniendo dirección por ID: {}", direccion_id);

        StringBuilder query = new StringBuilder(SELECT_DIRECCION_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("d.direccion_id = :direccion_id");
        params.addValue("direccion_id", direccion_id);

        return this.namedParameterJdbcTemplate.queryForObject(query.toString(), params, new RMDireccion());
    }

    @Override
    public List<Direccion> getDireccionesPorCliente(Integer cliente_id) {

        log.info("Obteniendo direcciones del cliente ID: {}", cliente_id);

        StringBuilder query = new StringBuilder(SELECT_DIRECCION_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("d.cliente_id = :cliente_id");
        params.addValue("cliente_id", cliente_id);

        return this.namedParameterJdbcTemplate.query(query.toString(), params, new RMDireccion());
    }

    // POST
    @Override
    public Integer createDireccion(Direccion d) {

        log.info("Creando nueva dirección para cliente ID: {}", d.getCliente_id());

        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(this.jdbcTemplate);

        List<String> columnas = new ArrayList<>();
        columnas.add("cliente_id");
        columnas.add("nombre_direccion");
        columnas.add("pais");
        columnas.add("codigo_postal");
        columnas.add("calle");
        columnas.add("numero_exterior");
        columnas.add("numero_interior");
        columnas.add("referencias");
        columnas.add("colonia");
        columnas.add("municipio");
        columnas.add("estado");
        columnas.add("telefono");

        simpleInsert.setTableName("direccion");
        simpleInsert.setColumnNames(columnas);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("cliente_id", d.getCliente_id());
        parametros.put("nombre_direccion", d.getNombre_direccion());
        parametros.put("pais", d.getPais());
        parametros.put("codigo_postal", d.getCodigo_postal());
        parametros.put("calle", d.getCalle());
        parametros.put("numero_exterior", d.getNumero_exterior());
        parametros.put("numero_interior", d.getNumero_interior());
        parametros.put("referencias", d.getReferencias());
        parametros.put("colonia", d.getColonia());
        parametros.put("municipio", d.getMunicipio());
        parametros.put("estado", d.getEstado());
        parametros.put("telefono", d.getTelefono());

        simpleInsert.setGeneratedKeyName("direccion_id");

        Number direccion_id = simpleInsert.executeAndReturnKey(parametros);

        return direccion_id.intValue();
    }

    // PUT
    @Override
    public void updateDireccion(Direccion d) {

        log.info("Actualizando dirección ID: {}", d.getDireccion_id());

        StringBuilder query = new StringBuilder(UPDATE_DIRECCION_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" SET ");
        query.append("nombre_direccion = :nombre_direccion, ");
        params.addValue("nombre_direccion", d.getNombre_direccion());

        query.append("pais = :pais, ");
        params.addValue("pais", d.getPais());

        query.append("codigo_postal = :codigo_postal, ");
        params.addValue("codigo_postal", d.getCodigo_postal());

        query.append("calle = :calle, ");
        params.addValue("calle", d.getCalle());

        query.append("numero_exterior = :numero_exterior, ");
        params.addValue("numero_exterior", d.getNumero_exterior());

        query.append("numero_interior = :numero_interior, ");
        params.addValue("numero_interior", d.getNumero_interior());

        query.append("referencias = :referencias, ");
        params.addValue("referencias", d.getReferencias());

        query.append("colonia = :colonia, ");
        params.addValue("colonia", d.getColonia());

        query.append("municipio = :municipio, ");
        params.addValue("municipio", d.getMunicipio());

        query.append("estado = :estado, ");
        params.addValue("estado", d.getEstado());

        query.append("telefono = :telefono ");
        params.addValue("telefono", d.getTelefono());

        query.append(" WHERE ");
        query.append("d.direccion_id = :direccion_id");
        params.addValue("direccion_id", d.getDireccion_id());

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }

    // DELETE
    @Override
    public void deleteDireccionFisica(Integer direccion_id) {

        log.info("Eliminando dirección ID: {}", direccion_id);

        StringBuilder query = new StringBuilder(DELETE_DIRECCION_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("d.direccion_id = :direccion_id");
        params.addValue("direccion_id", direccion_id);

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }
    
}
