package com.example.demo.tablas.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.tablas.model.Carrito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class CarritoRepositoryImpl implements CarritoRepository {

    private static final Logger log = LoggerFactory.getLogger(CarritoRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /* CONSULTAS (QUERY) MYSQL */
    //---> CONSULTAS - CARRITO
    private final String SELECT_CARRITO_QUERY = "SELECT cc.* FROM carrito_compra cc";
    private final String UPDATE_CARRITO_QUERY = "UPDATE carrito_compra cc";
    private final String DELETE_CARRITO_QUERY = "DELETE FROM carrito_compra cc";

    @Autowired
    public CarritoRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = npJdbcTemplate;
    }

    /* REPOSITORIO - CARRITO */

    // GET
    @Override
    public List<Carrito> getTotalidadCarritos() {

        log.info("Obteniendo lista total de registros de carrito...");

        return this.jdbcTemplate.query(SELECT_CARRITO_QUERY, new RMCarrito());
    }

    @Override
    public Carrito getCarritoPorId(Integer carrito_compra_id) {

        log.info("Obteniendo carrito por ID: {}", carrito_compra_id);

        StringBuilder query = new StringBuilder(SELECT_CARRITO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("cc.carrito_compra_id = :carrito_compra_id");
        params.addValue("carrito_compra_id", carrito_compra_id);

        return this.namedParameterJdbcTemplate.queryForObject(
                query.toString(),
                params,
                new RMCarrito()
        );
    }

    @Override
    public List<Carrito> getCarritosPorCliente(Integer cliente_id) {

        log.info("Obteniendo registros de carrito para cliente ID: {}", cliente_id);

        StringBuilder query = new StringBuilder(SELECT_CARRITO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("cc.cliente_id = :cliente_id");
        params.addValue("cliente_id", cliente_id);

        return this.namedParameterJdbcTemplate.query(
                query.toString(),
                params,
                new RMCarrito()
        );
    }

    // POST
    @Override
    public Integer createCarrito(Carrito c) {

        log.info("Creando registro en carrito para cliente ID: {}, producto ID: {}",
                c.getCliente_id(), c.getProducto_id());

        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(this.jdbcTemplate);

        List<String> columnas = new ArrayList<>();
        columnas.add("cliente_id");
        columnas.add("producto_id");
        columnas.add("cantidad");
        columnas.add("precio");
        columnas.add("subtotal");

        simpleInsert.setTableName("carrito_compra"); // cambia a "carrito" si tu tabla se llama así
        simpleInsert.setColumnNames(columnas);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("cliente_id", c.getCliente_id());
        parametros.put("producto_id", c.getProducto_id());
        parametros.put("cantidad", c.getCantidad());
        parametros.put("precio", c.getPrecio());
        parametros.put("subtotal", c.getSubtotal());

        simpleInsert.setGeneratedKeyName("carrito_compra_id");

        Number carrito_compra_id = simpleInsert.executeAndReturnKey(parametros);

        return carrito_compra_id.intValue();
    }

    // PUT
    @Override
    public void updateCarrito(Carrito c) {

        log.info("Actualizando registro de carrito ID: {}", c.getCarrito_compra_id());

        StringBuilder query = new StringBuilder(UPDATE_CARRITO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" SET ");
        query.append("cliente_id = :cliente_id, ");
        params.addValue("cliente_id", c.getCliente_id());

        query.append("producto_id = :producto_id, ");
        params.addValue("producto_id", c.getProducto_id());

        query.append("cantidad = :cantidad, ");
        params.addValue("cantidad", c.getCantidad());

        query.append("precio = :precio, ");
        params.addValue("precio", c.getPrecio());

        query.append("subtotal = :subtotal ");
        params.addValue("subtotal", c.getSubtotal());

        query.append(" WHERE ");
        query.append("cc.carrito_compra_id = :carrito_compra_id");
        params.addValue("carrito_compra_id", c.getCarrito_compra_id());

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }

    // DELETE
    @Override
    public void deleteCarritoFisico(Integer carrito_compra_id) {

        log.info("Eliminando registro de carrito ID: {}", carrito_compra_id);

        StringBuilder query = new StringBuilder(DELETE_CARRITO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("cc.carrito_compra_id = :carrito_compra_id");
        params.addValue("carrito_compra_id", carrito_compra_id);

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }
}
