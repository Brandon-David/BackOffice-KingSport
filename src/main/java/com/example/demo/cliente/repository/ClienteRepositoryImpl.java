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
import com.example.demo.cliente.model.Favoritos;
import com.example.demo.producto.model.Producto;
import com.example.demo.producto.repository.RMProducto;


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

    /* CONSULTAS (QUERY) MYSQL */
    //---> CONSULTAS - FAVORITOS
    private final String SELECT_FAVORITOS_QUERY = "SELECT f.* FROM favoritos f";
    private final String DELETE_FAVORITOS_QUERY = "DELETE FROM favoritos f";
    
    private final String SELECT_PRODUCTOS_FAVORITOS_QUERY = "SELECT p.* FROM favoritos f ";
    
    @Autowired
    public ClienteRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = npJdbcTemplate;
    }

    /* REPOSITORIO - CLIENTE */

    //GET
    
    @Override
    public List<Cliente> getTotalidadClientes(String estado) {

    	log.info("Obteniendo lista total de clientes...");
    	
    	StringBuilder query = new StringBuilder(SELECT_CLIENTE_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();
    	
        if(!estado.equals("*")) {
        	
        	query.append(" WHERE ");
            query.append("c.estado = :estado");
            params.addValue("estado", estado);
        }

        return this.namedParameterJdbcTemplate.query(query.toString(), params, new RMCliente());
    }

    @Override
    public Cliente getCliente(Integer cliente_id) {

        log.info("Obteniendo cliente por ID: {}", cliente_id);

        StringBuilder query = new StringBuilder(SELECT_CLIENTE_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("c.cliente_id = :cliente_id");
        params.addValue("cliente_id", cliente_id);

        return this.namedParameterJdbcTemplate.queryForObject(query.toString(), params, new RMCliente());
    }

    //POST
    
    @Override
    public Integer createCliente(Cliente c) {

        log.info("Creando cliente nuevo...");

        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(this.jdbcTemplate);

        List<String> columnas = new ArrayList<>();
        columnas.add("direccion_principal_id");
        columnas.add("nombre_completo");
        columnas.add("correo");
        columnas.add("telefono");
        columnas.add("contrasena");

        simpleInsert.setTableName("cliente");
        simpleInsert.setColumnNames(columnas);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("direccion_principal_id", c.getDireccion_principal_id());
        parametros.put("nombre_completo", c.getNombre_completo());
        parametros.put("correo", c.getCorreo());
        parametros.put("telefono", c.getTelefono());
        parametros.put("contrasena", c.getContrasena());

        System.out.println(c.getContrasena());
        
        simpleInsert.setGeneratedKeyName("cliente_id");

        Number cliente_id = simpleInsert.executeAndReturnKey(parametros);

        return cliente_id.intValue();
    }
    
    //PUT

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

        query.append("telefono = :telefono, ");
        params.addValue("telefono", c.getTelefono());
        
        query.append("correo = :correo ");
        params.addValue("correo", c.getCorreo());

        query.append(" WHERE ");
        query.append("c.cliente_id = :cliente_id");
        params.addValue("cliente_id", c.getCliente_id());

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }
    
    @Override
	public void updateEstadoCliente(Integer cliente_id, String estado) {
		
		log.info("Actualizando de estado de cliente ID: {}", cliente_id);

        StringBuilder query = new StringBuilder(UPDATE_CLIENTE_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" SET ");
        query.append("estado = :estado ");
        params.addValue("estado", estado);

        query.append(" WHERE ");
        query.append("c.cliente_id = :cliente_id");
        params.addValue("cliente_id", cliente_id);

        this.namedParameterJdbcTemplate.update(query.toString(), params);
	}
    
    @Override
    public void updateDireccionPredeterminada(Integer cliente_id, Integer direccion_id) {

        log.info("Actualizando dirección principal del cliente ID: {}", cliente_id);

        StringBuilder query = new StringBuilder(UPDATE_CLIENTE_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" SET ");
        query.append("direccion_principal_id = :direccion_principal_id ");
        params.addValue("direccion_principal_id", direccion_id);

        query.append(" WHERE ");
        query.append("c.cliente_id = :cliente_id");
        params.addValue("cliente_id", cliente_id);

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }
      
    //DELETE

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

        return this.namedParameterJdbcTemplate.queryForObject(query.toString(), params, new RMFavoritos());
    }
    
    @Override
    public List<Favoritos> getFavoritosPorCliente(Integer cliente_id) {

        log.info("Obteniendo favoritos del cliente ID: {}", cliente_id);

        StringBuilder query = new StringBuilder(SELECT_FAVORITOS_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("f.cliente_id = :cliente_id");
        params.addValue("cliente_id", cliente_id);

        return this.namedParameterJdbcTemplate.query(query.toString(), params, new RMFavoritos());
    }
    
    @Override
    public List<Producto> getProductosFavoritosPorCliente(Integer cliente_id) {

        log.info("Obteniendo productos favoritos del cliente ID: {}", cliente_id);

        StringBuilder query = new StringBuilder(SELECT_PRODUCTOS_FAVORITOS_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append("JOIN producto p ON p.producto_id = f.producto_id");
        query.append(" WHERE ");
        query.append("f.cliente_id = :cliente_id");
        params.addValue("cliente_id", cliente_id);

        return this.namedParameterJdbcTemplate.query(query.toString(), params, new RMProducto());
    }

    // POST
    @Override
    public Integer createFavoritos(Integer cliente_id, Integer producto_id) {

        log.info("Creando favorito para cliente ID: {}, producto ID: {}", cliente_id, producto_id);

        SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(this.jdbcTemplate);

        List<String> columnas = new ArrayList<>();
        columnas.add("cliente_id");
        columnas.add("producto_id");

        simpleInsert.setTableName("favoritos");
        simpleInsert.setColumnNames(columnas);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("cliente_id", cliente_id);
        parametros.put("producto_id", producto_id);

        simpleInsert.setGeneratedKeyName("favoritos_id");

        Number favoritos_id = simpleInsert.executeAndReturnKey(parametros);

        return favoritos_id.intValue();
    }

    // DELETE
    @Override
    public void deleteFavoritosFisico(Integer cliente_id, Integer producto_id) {

        log.info("Eliminando favorito de cliente ID: {}", cliente_id);

        StringBuilder query = new StringBuilder(DELETE_FAVORITOS_QUERY);
        MapSqlParameterSource params = new MapSqlParameterSource();

        query.append(" WHERE ");
        query.append("f.cliente_id = :cliente_id AND f.producto_id = :producto_id ");
        params.addValue("cliente_id", cliente_id);
        params.addValue("producto_id", producto_id);

        this.namedParameterJdbcTemplate.update(query.toString(), params);
    }
    
    
}
