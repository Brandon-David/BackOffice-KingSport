package com.example.demo.diseño.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.diseño.model.Diseño;
import com.example.demo.usuario.repository.RMPerfil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class DiseñoRepositoryImpl implements DiseñoRepository {

    private static final Logger log = LoggerFactory.getLogger(DiseñoRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /* CONSULTAS (QUERY) MYSQL */
    //---> CONSULTAS - DISEÑO
    private final String SELECT_DISEÑO_QUERY = "SELECT * FROM diseno d";
    private final String UPDATE_DISEÑO_QUERY = "UPDATE diseno d";
    private final String DELETE_DISEÑO_QUERY = "DELETE FROM diseno d";

    @Autowired
    public DiseñoRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate) {
    	
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = npJdbcTemplate;
    }

    /* REPOSITORIO - DISEÑO */

    // GET
    @Override
    public List<Diseño> getTotalidadDiseños(String estado) {

        log.info("Obteniendo lista total de diseños...");
        
        StringBuilder query = new StringBuilder(SELECT_DISEÑO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        if(!estado.equals("*")) {
			
			query.append(" WHERE ");
			query.append("d.estado = :estado");
			params.addValue("estado", estado);
		}
        
        return this.namedParameterJdbcTemplate.query(query.toString(), params, new RMDiseño());
    }

    @Override
    public Diseño getDiseño(Integer diseno_id) {

        log.info("Obteniendo diseño por ID: {}", diseno_id);

        StringBuilder query = new StringBuilder(SELECT_DISEÑO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("d.diseno_id = :diseno_id");
        params.addValue("diseno_id", diseno_id);

        return this.namedParameterJdbcTemplate.queryForObject(query.toString(), params, new RMDiseño());
    }

    @Override
    public List<Diseño> getDiseñosPorCliente(Integer cliente_id) {

        log.info("Obteniendo diseños del cliente ID: {}", cliente_id);

        StringBuilder query = new StringBuilder(SELECT_DISEÑO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("d.cliente_id = :cliente_id");
        params.addValue("cliente_id", cliente_id);

        return this.namedParameterJdbcTemplate.query(query.toString(), params, new RMDiseño());
    }

    // POST
    @Override
    public Integer createDiseño(Diseño d) {

        log.info("Creando diseño para cliente ID: {}", d.getCliente_id());

        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(this.jdbcTemplate);

        List<String> columnas = new ArrayList<>();
        columnas.add("cliente_id");
        columnas.add("nombre_equipo");
        columnas.add("posicion");
        columnas.add("numeracion");
        columnas.add("tipo_cuello");
        columnas.add("descripcion_idea");
        columnas.add("boceto");

        simpleInsert.setTableName("diseno");
        simpleInsert.setColumnNames(columnas);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("cliente_id", d.getCliente_id());
        parametros.put("nombre_equipo", d.getNombre_equipo());
        parametros.put("posicion", d.getPosicion());
        parametros.put("numeracion", d.getNumeracion());
        parametros.put("tipo_cuello", d.getTipo_cuello());
        parametros.put("descripcion_idea", d.getDescripcion_idea());
        parametros.put("boceto", d.getBoceto());

        simpleInsert.setGeneratedKeyName("diseno_id");

        Number diseno_id = simpleInsert.executeAndReturnKey(parametros);

        return diseno_id.intValue();
    }

    // PUT
    @Override
    public void updateDiseño(Diseño d) {

        log.info("Actualizando diseño ID: {}", d.getDiseno_id());

        StringBuilder query = new StringBuilder(UPDATE_DISEÑO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" SET ");
        query.append("nombre_equipo = :nombre_equipo, ");
        params.addValue("nombre_equipo", d.getNombre_equipo());

        query.append("posicion = :posicion, ");
        params.addValue("posicion", d.getPosicion());

        query.append("numeracion = :numeracion, ");
        params.addValue("numeracion", d.getNumeracion());

        query.append("tipo_cuello = :tipo_cuello, ");
        params.addValue("tipo_cuello", d.getTipo_cuello());

        query.append("descripcion_idea = :descripcion_idea, ");
        params.addValue("descripcion_idea", d.getDescripcion_idea());

        query.append("boceto = :boceto, ");
        params.addValue("boceto", d.getBoceto());

        /*
        query.append("estado = :estado ");
        params.addValue("estado", d.getEstado());
        */
        
        query.append("estado = :estado ");
        params.addValue("estado", "Pendiente");

        query.append(" WHERE ");
        query.append("d.diseno_id = :diseno_id");
        params.addValue("diseno_id", d.getDiseno_id());

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }

    public void updateEstadoDiseño(Integer diseno_id, String estado) {
    	
    	log.info("Actualizando del estado del diseño ID: {}", diseno_id);

        StringBuilder query = new StringBuilder(UPDATE_DISEÑO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" SET ");
        query.append("estado = :estado ");
        params.addValue("estado", estado);

        query.append(" WHERE ");
        query.append("diseno_id = :diseno_id");
        params.addValue("diseno_id", diseno_id);

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }
    
    // DELETE
    @Override
    public void deleteDiseñoFisico(Integer diseno_id) {

        log.info("Eliminando diseño ID: {}", diseno_id);

        StringBuilder query = new StringBuilder(DELETE_DISEÑO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("d.diseno_id = :diseno_id");
        params.addValue("diseno_id", diseno_id);

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }
}
