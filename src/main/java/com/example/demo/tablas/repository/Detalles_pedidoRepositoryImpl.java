package com.example.demo.tablas.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.tablas.model.Detalles_pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class Detalles_pedidoRepositoryImpl implements Detalles_pedidoRepository {

    private static final Logger log = LoggerFactory.getLogger(Detalles_pedidoRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /* CONSULTAS (QUERY) MYSQL */
    //---> CONSULTAS - DETALLE_PEDIDO
    private final String SELECT_DETALLE_QUERY = "SELECT dp.* FROM detalle_pedido dp";
    private final String UPDATE_DETALLE_QUERY = "UPDATE detalle_pedido dp";
    private final String DELETE_DETALLE_QUERY = "DELETE FROM detalle_pedido dp";

    @Autowired
    public Detalles_pedidoRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = npJdbcTemplate;
    }

    /* REPOSITORIO - DETALLE_PEDIDO */

    // GET
    @Override
    public List<Detalles_pedido> getTotalidadDetalles() {

        log.info("Obteniendo lista total de detalles_pedido...");

        return this.jdbcTemplate.query(SELECT_DETALLE_QUERY, new RMDetalles_pedido());
    }

    @Override
    public Detalles_pedido getDetallePorId(Integer detalle_pedido_id) {

        log.info("Obteniendo detalle_pedido por ID: {}", detalle_pedido_id);

        StringBuilder query = new StringBuilder(SELECT_DETALLE_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("dp.detalle_pedido_id = :detalle_pedido_id");
        params.addValue("detalle_pedido_id", detalle_pedido_id);

        return this.namedParameterJdbcTemplate.queryForObject(
                query.toString(),
                params,
                new RMDetalles_pedido()
        );
    }

    @Override
    public List<Detalles_pedido> getDetallesPorPedido(Integer pedido_id) {

        log.info("Obteniendo detalles de pedido ID: {}", pedido_id);

        StringBuilder query = new StringBuilder(SELECT_DETALLE_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("dp.pedido_id = :pedido_id");
        params.addValue("pedido_id", pedido_id);

        return this.namedParameterJdbcTemplate.query(
                query.toString(),
                params,
                new RMDetalles_pedido()
        );
    }

    // POST
    @Override
    public Integer createDetalle(Detalles_pedido d) {

        log.info("Creando detalle_pedido para pedido ID: {}, producto ID: {}",
                d.getPedido_id(), d.getProducto_id());

        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(this.jdbcTemplate);

        List<String> columnas = new ArrayList<>();
        columnas.add("pedido_id");
        columnas.add("producto_id");
        columnas.add("cantidad");
        columnas.add("precio");
        columnas.add("talla");
        columnas.add("personalizacion");
        columnas.add("subtotal");

        simpleInsert.setTableName("detalle_pedido");
        simpleInsert.setColumnNames(columnas);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("pedido_id", d.getPedido_id());
        parametros.put("producto_id", d.getProducto_id());
        parametros.put("cantidad", d.getCantidad());
        parametros.put("precio", d.getPrecio());
        parametros.put("talla", d.getTalla());
        parametros.put("personalizacion", d.getPersonalizacion());
        parametros.put("subtotal", d.getSubtotal());

        simpleInsert.setGeneratedKeyName("detalle_pedido_id");

        Number detalle_pedido_id = simpleInsert.executeAndReturnKey(parametros);

        return detalle_pedido_id.intValue();
    }

    // PUT
    @Override
    public void updateDetalle(Detalles_pedido d) {

        log.info("Actualizando detalle_pedido ID: {}", d.getDetalle_pedido_id());

        StringBuilder query = new StringBuilder(UPDATE_DETALLE_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" SET ");
        query.append("cantidad = :cantidad, ");
        params.addValue("cantidad", d.getCantidad());

        query.append("precio = :precio, ");
        params.addValue("precio", d.getPrecio());

        query.append("talla = :talla, ");
        params.addValue("talla", d.getTalla());

        query.append("personalizacion = :personalizacion, ");
        params.addValue("personalizacion", d.getPersonalizacion());

        query.append("subtotal = :subtotal ");
        params.addValue("subtotal", d.getSubtotal());

        query.append(" WHERE ");
        query.append("dp.detalle_pedido_id = :detalle_pedido_id");
        params.addValue("detalle_pedido_id", d.getDetalle_pedido_id());

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }

    // DELETE
    @Override
    public void deleteDetalleFisico(Integer detalle_pedido_id) {

        log.info("Eliminando detalle_pedido ID: {}", detalle_pedido_id);

        StringBuilder query = new StringBuilder(DELETE_DETALLE_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("dp.detalle_pedido_id = :detalle_pedido_id");
        params.addValue("detalle_pedido_id", detalle_pedido_id);

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }
}
