package com.example.demo.tablas.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.tablas.model.Favoritos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class FavoritosRepositoryImpl implements FavoritosRepository {

    private static final Logger log = LoggerFactory.getLogger(FavoritosRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /* CONSULTAS (QUERY) MYSQL */
    //---> CONSULTAS - FAVORITOS
    private final String SELECT_FAVORITOS_QUERY = "SELECT f.* FROM favoritos f";
    private final String DELETE_FAVORITOS_QUERY = "DELETE FROM favoritos f";

    @Autowired
    public FavoritosRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = npJdbcTemplate;
    }

    /* REPOSITORIO - FAVORITOS */

    // GET
    @Override
    public List<Favoritos> getTotalidadFavoritos() {

        log.info("Obteniendo lista total de favoritos...");

        return this.jdbcTemplate.query(SELECT_FAVORITOS_QUERY, new RMFavoritos());
    }

    @Override
    public Favoritos getFavoritosPorId(Integer favoritos_id) {

        log.info("Obteniendo favorito por ID: {}", favoritos_id);

        StringBuilder query = new StringBuilder(SELECT_FAVORITOS_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("f.favoritos_id = :favoritos_id");
        params.addValue("favoritos_id", favoritos_id);

        return this.namedParameterJdbcTemplate.queryForObject(
                query.toString(),
                params,
                new RMFavoritos()
        );
    }

    @Override
    public List<Favoritos> getFavoritosPorCliente(Integer cliente_id) {

        log.info("Obteniendo favoritos del cliente ID: {}", cliente_id);

        StringBuilder query = new StringBuilder(SELECT_FAVORITOS_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("f.cliente_id = :cliente_id");
        params.addValue("cliente_id", cliente_id);

        return this.namedParameterJdbcTemplate.query(
                query.toString(),
                params,
                new RMFavoritos()
        );
    }

    // POST
    @Override
    public Integer createFavoritos(Favoritos f) {

        log.info("Creando favorito para cliente ID: {}, producto ID: {}",
                f.getCliente_id(), f.getProducto_id());

        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(this.jdbcTemplate);

        List<String> columnas = new ArrayList<>();
        columnas.add("cliente_id");
        columnas.add("producto_id");

        simpleInsert.setTableName("favoritos");
        simpleInsert.setColumnNames(columnas);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("cliente_id", f.getCliente_id());
        parametros.put("producto_id", f.getProducto_id());

        simpleInsert.setGeneratedKeyName("favoritos_id");

        Number favoritos_id = simpleInsert.executeAndReturnKey(parametros);

        return favoritos_id.intValue();
    }

    // DELETE
    @Override
    public void deleteFavoritosFisico(Integer favoritos_id) {

        log.info("Eliminando favorito ID: {}", favoritos_id);

        StringBuilder query = new StringBuilder(DELETE_FAVORITOS_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("f.favoritos_id = :favoritos_id");
        params.addValue("favoritos_id", favoritos_id);

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }
}
