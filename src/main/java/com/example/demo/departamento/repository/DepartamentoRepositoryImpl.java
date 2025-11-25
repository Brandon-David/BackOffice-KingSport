package com.example.demo.departamento.repository;

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

import com.example.demo.departamento.model.Categoria;
import com.example.demo.departamento.model.Departamento;
import com.example.demo.departamento.model.Subcategoria;

@Repository
public class DepartamentoRepositoryImpl implements DepartamentoRepository {

    private static final Logger log = LoggerFactory.getLogger(DepartamentoRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /* CONSULTAS (QUERY) MYSQL - DEPARTAMENTOS */
    private final String SELECT_DEPTO_QUERY = "SELECT d.* FROM departamento d";
    private final String UPDATE_DEPTO_QUERY = "UPDATE departamento d";
    private final String DELETE_DEPTO_QUERY = "DELETE FROM departamento d";

    private static final String SELECT_DEPTO_CATEGORIAS_SUB_QUERY =
    	    "SELECT " +
    	    "d.departamento_id, " +
    	    "d.nombre              AS d_nombre, " +
    	    "d.fecha_creacion      AS d_fecha_creacion, " +
    	    "d.fecha_actualizacion AS d_fecha_actualizacion, " +
    	    "c.categoria_id, " +
    	    "c.departamento_id     AS c_departamento_id, " +
    	    "c.nombre              AS c_nombre, " +
    	    "c.fecha_creacion      AS c_fecha_creacion, " +
    	    "c.fecha_actualizacion AS c_fecha_actualizacion, " +
    	    "sc.subcategoria_id, " +
    	    "sc.categoria_id        AS sc_categoria_id, " +
    	    "sc.nombre              AS sc_nombre, " +
    	    "sc.fecha_creacion      AS sc_fecha_creacion, " +
    	    "sc.fecha_actualizacion AS sc_fecha_actualizacion " +
    	    "FROM departamento d ";
    
    /* CONSULTAS (QUERY) MYSQL - CATEGORIA */
    private final String SELECT_CAT_QUERY = "SELECT c.* FROM categoria c";
    private final String UPDATE_CAT_QUERY = "UPDATE categoria c";
    private final String DELETE_CAT_QUERY = "DELETE FROM categoria c";
    
    /* CONSULTAS (QUERY) MYSQL - SUBCATEGORIAS */
    private final String SELECT_SUBCAT_QUERY = "SELECT s.* FROM subcategoria s";
    private final String UPDATE_SUBCAT_QUERY = "UPDATE subcategoria s";
    private final String DELETE_SUBCAT_QUERY = "DELETE FROM subcategoria s";
    
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
    public Departamento getDepartamento(Integer departamento_id) {

        log.info("Obteniendo departamento ID: {}", departamento_id);

        StringBuilder query = new StringBuilder(SELECT_DEPTO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE d.departamento_id = :departamento_id");
        params.addValue("departamento_id", departamento_id);

        return this.namedParameterJdbcTemplate.queryForObject(query.toString(), params, new RMDepartamento());
    }
    
    @Override
    public List<Departamento> getTotalidadDepartamentosCatSub() {

        log.info("Obteniendo lista total de departamentos, sus categorias y categotias");

        StringBuilder query = new StringBuilder(SELECT_DEPTO_CATEGORIAS_SUB_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append("LEFT JOIN categoria c ON d.departamento_id = c.departamento_id ");
	    query.append("LEFT JOIN subcategoria sc ON c.categoria_id = sc.categoria_id ");

	    /*
        query.append("WHERE c.departamento_id = :departamento_id");
        params.addValue("departamento_id", departamento_id);
        */
	    
        query.append("ORDER BY d.departamento_id, c.categoria_id, sc.subcategoria_id");
        

        return this.namedParameterJdbcTemplate.queryForObject(query.toString(), params, new RSEDepartamentoCatSub());
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
    
    /* REPOSITORIO - CATEGORIA */

    @Override
    public List<Categoria> getTotalidadCategorias() {

        log.info("Obteniendo lista total de categorias...");

        return this.jdbcTemplate.query(SELECT_CAT_QUERY, new RMCategoria());
    }

    @Override
    public Categoria getCategoria(Integer categoria_id) {

        log.info("Obteniendo categoria ID: {}", categoria_id);

        StringBuilder query = new StringBuilder(SELECT_CAT_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE c.categoria_id = :categoria_id");
        params.addValue("categoria_id", categoria_id);

        return this.namedParameterJdbcTemplate.queryForObject(query.toString(), params, new RMCategoria());
    }

    @Override
    public Integer createCategoria(Categoria c) {

        log.info("Creando categoria: {}", c.getNombre());

        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(this.jdbcTemplate);

        List<String> columnas = new ArrayList<>();
        columnas.add("nombre");
        columnas.add("departamento_id");

        simpleInsert.setTableName("categoria");
        simpleInsert.setColumnNames(columnas);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nombre", c.getNombre());
        parametros.put("departamento_id", c.getDepartamento_id());

        simpleInsert.setGeneratedKeyName("categoria_id");

        Number categoria_id = simpleInsert.executeAndReturnKey(parametros);

        return categoria_id.intValue();
    }

    @Override
    public void updateCategoria(Categoria c) {

        log.info("Actualizando categoria ID: {}", c.getCategoria_id());

        StringBuilder query = new StringBuilder(UPDATE_CAT_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" SET nombre = :nombre, ");
        params.addValue("nombre", c.getNombre());
        
        query.append(" departamento_id = :departamento_id ");
        params.addValue("departamento_id", c.getDepartamento_id());

        query.append(" WHERE c.categoria_id = :categoria_id ");
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
    
    /* REPOSITORIO - SUBCATEGORIA */
    
    @Override
    public List<Subcategoria> getTotalidadSubcategorias() {

        log.info("Obteniendo lista total de subcategorias...");

        return this.jdbcTemplate.query(SELECT_SUBCAT_QUERY, new RMSubcategoria());
    }

    @Override
    public Subcategoria getSubcategoria(Integer subcategoria_id) {

        log.info("Obteniendo subcategoria ID: {}", subcategoria_id);

        StringBuilder query = new StringBuilder(SELECT_SUBCAT_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE s.subcategoria_id = :subcategoria_id");
        params.addValue("subcategoria_id", subcategoria_id);

        return this.namedParameterJdbcTemplate.queryForObject(query.toString(), params, new RMSubcategoria());
    }

    @Override
    public Integer createSubcategoria(Subcategoria s) {

        log.info("Creando subcategoria: {}", s.getNombre());

        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(this.jdbcTemplate);

        List<String> columnas = new ArrayList<>();
        columnas.add("nombre");
        columnas.add("categoria_id");

        simpleInsert.setTableName("subcategoria");
        simpleInsert.setColumnNames(columnas);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nombre", s.getNombre());
        parametros.put("categoria_id", s.getCategoria_id());

        simpleInsert.setGeneratedKeyName("subcategoria_id");

        Number subcategoria_id = simpleInsert.executeAndReturnKey(parametros);

        return subcategoria_id.intValue();
    }

    @Override
    public void updateSubcategoria(Subcategoria s) {

        log.info("Actualizando subcategoria ID: {}", s.getSubcategoria_id());

        StringBuilder query = new StringBuilder(UPDATE_SUBCAT_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" SET nombre = :nombre, ");
        params.addValue("nombre", s.getNombre());
        
        query.append(" categoria_id = :categoria_id ");
        params.addValue("categoria_id", s.getCategoria_id());

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
