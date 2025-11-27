package com.example.demo.pedidos.repository;

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

import com.example.demo.pedidos.model.Envio;
import com.example.demo.pedidos.model.Pedido;

@Repository
public class PedidoRepositoryImpl implements PedidoRepository {

    private static final Logger log = LoggerFactory.getLogger(PedidoRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /* CONSULTAS (QUERY) MYSQL - PEDIDO */
    private final String SELECT_PEDIDO_QUERY = "SELECT p.* FROM pedido p";
    private final String UPDATE_PEDIDO_QUERY = "UPDATE pedido p";
    private final String DELETE_PEDIDO_QUERY = "DELETE FROM pedido p";
    
    private final String SELECT_PEDIDO_CLIENTE = 
    		"SELECT p.*, "
    		+ "c.cliente_id, "
    		+ "c.nombre_completo, "
    		+ "c.telefono, "
    		+ "c.correo "
    		+ "FROM pedido p";
    		
    
    /* CONSULTAS (QUERY) MYSQL - ENVIO */
    private final String SELECT_ENVIO_QUERY = "SELECT e.* FROM envio e";

    @Autowired
    public PedidoRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate) {
        
    	this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = npJdbcTemplate;
    }

    /* REPOSITORIO - PEDIDO */

    @Override
    public List<Pedido> getTotalidadPedidos() {

        log.info("Obteniendo lista total de pedidos...");

        return this.jdbcTemplate.query(SELECT_PEDIDO_QUERY, new RMPedido());
    }
    
    public List<Pedido> getTotalidadPedidosCliente(){
    	
    	log.info("Obteniendo lista total de pedidos con su cliente asociado...");
    	
    	 StringBuilder query = new StringBuilder(SELECT_PEDIDO_CLIENTE);
         MapSqlParameterSource params = new MapSqlParameterSource();
         
         query.append("JOIN cliente c ON c.cliente_id = p.cliente_id ");
         
         return this.namedParameterJdbcTemplate.query(query.toString(), params, new RMPedidoCliente());
    }

    @Override
    public Pedido getPedidoPorId(Integer pedido_id) {

        log.info("Obteniendo pedido ID: {}", pedido_id);

        StringBuilder query = new StringBuilder(SELECT_PEDIDO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE p.pedido_id = :pedido_id");
        params.addValue("pedido_id", pedido_id);

        return this.namedParameterJdbcTemplate.queryForObject(query.toString(), params, new RMPedido());
    }

    @Override
    public List<Pedido> getPedidosPorCliente(Integer cliente_id) {

        log.info("Obteniendo pedidos del cliente ID: {}", cliente_id);

        StringBuilder query = new StringBuilder(SELECT_PEDIDO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE p.cliente_id = :cliente_id");
        params.addValue("cliente_id", cliente_id);

        return this.namedParameterJdbcTemplate.query(query.toString(), params, new RMPedido());
    }
    

    @Override
    public Integer createPedido(Pedido p) {

        log.info("Creando pedido para cliente ID: {}", p.getCliente_id());

        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(this.jdbcTemplate);

        List<String> columnas = new ArrayList<>();
        columnas.add("cliente_id");
        columnas.add("estado");
        columnas.add("subtotal");
        columnas.add("costo_envio");

        simpleInsert.setTableName("pedido");
        simpleInsert.setColumnNames(columnas);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("cliente_id", p.getCliente_id());
        parametros.put("estado", p.getEstado());
        parametros.put("subtotal", p.getSubtotal());
        parametros.put("costo_envio", p.getCosto_envio());

        simpleInsert.setGeneratedKeyName("pedido_id");

        Number pedido_id = simpleInsert.executeAndReturnKey(parametros);

        return pedido_id.intValue();
    }

    @Override
    public void updatePedido(Pedido p) {

        log.info("Actualizando pedido ID: {}", p.getPedido_id());

        StringBuilder query = new StringBuilder(UPDATE_PEDIDO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" SET ");
        query.append("estado = :estado, ");
        params.addValue("estado", p.getEstado());

        query.append("subtotal = :subtotal, ");
        params.addValue("subtotal", p.getSubtotal());

        query.append("costo_envio = :costo_envio, ");
        params.addValue("costo_envio", p.getCosto_envio());

        query.append(" WHERE p.pedido_id = :pedido_id");
        params.addValue("pedido_id", p.getPedido_id());

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }

    @Override
    public void deletePedidoFisico(Integer pedido_id) {

        log.info("Eliminando pedido ID: {}", pedido_id);

        StringBuilder query = new StringBuilder(DELETE_PEDIDO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE p.pedido_id = :pedido_id");
        params.addValue("pedido_id", pedido_id);

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }
    
    /* REPOSITORIO - PEDIDO */
    
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

        return this.namedParameterJdbcTemplate.queryForObject(query.toString(), params, new RMEnvio());
    }

    @Override
    public List<Envio> getEnviosPorPedido(Integer pedido_id) {

        log.info("Obteniendo envios del pedido ID: {}", pedido_id);

        StringBuilder query = new StringBuilder(SELECT_ENVIO_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE e.pedido_id = :pedido_id");
        params.addValue("pedido_id", pedido_id);

        return this.namedParameterJdbcTemplate.query(query.toString(), params, new RMEnvio());
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
    
}
