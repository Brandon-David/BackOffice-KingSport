package com.example.demo.usuario.repository;

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

import com.example.demo.usuario.model.Perfil;
import com.example.demo.usuario.model.Usuario;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository{

	private static final Logger log = LoggerFactory.getLogger(UsuarioRepositoryImpl.class);

	private JdbcTemplate jdbcTemplate;
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	/*CONSULTAS (QUERY) MYSQL */
	//--> CONSULTAS - USUARIO
	private final String SELECT_USUARIO_QUERY = "SELECT u.* FROM usuario u";
	
	private static final String SELECT_USUARIO_PERFIL_QUERY =
		    "SELECT "
		  + "u.usuario_id, "
		  + "u.nombre, "
		  + "u.apellido_paterno, "
		  + "u.apellido_materno, "
		  + "u.correo_usuario, "
		  + "u.contrasena, "
		  + "u.estado_usuario, "
		  + "u.fecha_creacion AS u_fecha_creacion, "
		  + "u.fecha_actualizacion AS u_fecha_actualizacion, "
		  + "p.perfil_id, "
		  + "p.rol, "
		  + "p.descripcion_perfil, "
		  + "p.estado_perfil, "
		  + "p.fecha_creacion AS p_fecha_creacion, "
		  + "p.fecha_actualizacion AS p_fecha_actualizacion "
		  + "FROM usuario u ";
	
	private final String UPDATE_USUARIO_QUERY = "UPDATE usuario u";
	
	private final String DELETE_USUARIO_QUERY = "DELETE FROM usuario u";
	
	//--> CONSULTAS - PERFIL
	
	private final String SELECT_PERFIL_QUERY = "SELECT p.* FROM perfil p";
	
	//--> CONSULTAS - USUARIO_PERFIL
	
	private final String UPDATE_USUARIOPERFIL_QUERY = "UPDATE usuario_perfil up";
	
	@Autowired
	public UsuarioRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate) {
		
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = npJdbcTemplate;
	}

	/*REPOSITORIO - USUARIO*/
	
	@Override
	public List<Usuario> getTotalidadUsuarios(String estado) {

	    StringBuilder query = new StringBuilder(SELECT_USUARIO_PERFIL_QUERY);
	    MapSqlParameterSource params = new MapSqlParameterSource();

	    query.append("JOIN usuario_perfil up ON u.usuario_id = up.usuario_id ");
	    query.append("JOIN perfil p ON p.perfil_id = up.perfil_id ");

	    if (!"*".equals(estado)) {
	        query.append("WHERE u.estado_usuario = :estado_usuario ");
	        params.addValue("estado_usuario", estado);
	    }

	    System.out.println("SQL → " + query);
	    System.out.println("PARAMS → " + params);

	    return this.namedParameterJdbcTemplate.query(query.toString(), params, new RMUsuarioPerfil());
	}
	
	@Override
	public List<Usuario> getUsuario(Integer usuario_id) {
		
		StringBuilder query = new StringBuilder(SELECT_USUARIO_QUERY);
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		query.append(" WHERE ");
		query.append("u.usuario_id = :usuario_id");
		params.addValue("usuario_id", usuario_id);		

		return this.namedParameterJdbcTemplate.query(query.toString(), params, new RMUsuario());
	}
	
	@Override
	public Integer createUsuario(Usuario us) {
		
		SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(this.jdbcTemplate);

		List<String> columnas = new ArrayList<>();
		columnas.add("nombre");
		columnas.add("apellido_paterno");
		columnas.add("apellido_materno");
		columnas.add("correo_usuario");
		columnas.add("contrasena");

		simpleInsert.setTableName("usuario");
		simpleInsert.setColumnNames(columnas);

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("nombre", us.getNombre());
		parametros.put("apellido_paterno", us.getApellido_paterno());
		parametros.put("apellido_materno", us.getApellido_materno());
		parametros.put("correo_usuario", us.getCorreo_usuario());
		parametros.put("contrasena", us.getContrasena());
		
		simpleInsert.setGeneratedKeyName("usuario_id");
		Number usuario_id = simpleInsert.executeAndReturnKey(parametros);

		return usuario_id.intValue();
	}
	
	@Override
	public void createUsuarioPerfil(Integer usuario_id, Integer perfil_id) {
		
		SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(this.jdbcTemplate);
		List<String> columnas = new ArrayList<>();

		columnas.add("usuario_id");
		columnas.add("perfil_id");

		simpleInsert.setTableName("usuario_perfil");
		simpleInsert.setColumnNames(columnas);

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("usuario_id", usuario_id);
		parametros.put("perfil_id", perfil_id);

		simpleInsert.setGeneratedKeyName("usuario_perfil_id");

		simpleInsert.execute(parametros);
	}
	
	@Override
	public void updateUsuario(Usuario us) {
		
		StringBuilder query = new StringBuilder(UPDATE_USUARIO_QUERY);
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		query.append(" SET ");
		query.append("nombre = :nombre, ");
		params.addValue("nombre", us.getNombre());

		query.append("apellido_paterno = :apellido_paterno, ");
		params.addValue("apellido_paterno", us.getApellido_paterno());

		query.append("apellido_materno = :apellido_materno, ");
		params.addValue("apellido_materno", us.getApellido_materno());

		query.append("correo_usuario = :correo_usuario, ");
		params.addValue("correo_usuario", us.getCorreo_usuario());

		query.append("estado_usuario = :estado_usuario ");
		params.addValue("estado_usuario", us.getEstado_usuario());

		query.append(" WHERE ");
		query.append("usuario_id = :usuario_id");
		params.addValue("usuario_id", us.getUsuario_id());

		System.out.println(params);
		
		this.namedParameterJdbcTemplate.update(query.toString(), params);
	}

	@Override
	public void updateEstadoUsuario(Integer usuario_id, String estado) {
		
		StringBuilder query = new StringBuilder(UPDATE_USUARIO_QUERY);
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		query.append(" SET ");
		query.append("estado_usuario = :estado_usuario ");
		params.addValue("estado_usuario", estado);

		query.append(" WHERE ");
		query.append("usuario_id = :usuario_id");
		params.addValue("usuario_id", usuario_id);

		this.namedParameterJdbcTemplate.update(query.toString(), params);
	}
	
	@Override
	public void updateUsuarioPerfil(Integer usuario_id, Integer perfil_id) {
		
		StringBuilder query = new StringBuilder(UPDATE_USUARIOPERFIL_QUERY);
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		query.append(" SET ");
		query.append("perfil_id = :perfil_id ");
		params.addValue("perfil_id", perfil_id);

		query.append(" WHERE ");
		query.append("usuario_id = :usuario_id");
		params.addValue("usuario_id", usuario_id);

		this.namedParameterJdbcTemplate.update(query.toString(), params);
	}
	
	@Override
	public void deleteUsuarioFisico(Integer usuario_id) {
		
		StringBuilder query = new StringBuilder(DELETE_USUARIO_QUERY);
		MapSqlParameterSource params = new MapSqlParameterSource();

		query.append(" WHERE ");
		query.append("usuario_id = :usuario_id");
		params.addValue("usuario_id", usuario_id);

		this.namedParameterJdbcTemplate.update(query.toString(), params);
	}

	/*REPOSITORIO - PERFIL*/
	
	@Override
	public List<Perfil> getTotalidadPerfiles(String estado) {
		
		StringBuilder query = new StringBuilder(SELECT_PERFIL_QUERY);
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		if(!estado.equals("*")) {
			
			query.append(" WHERE ");
			query.append("p.estado_perfil = :estado_perfil");
			params.addValue("estado_perfil", estado);
		}
		
		return this.namedParameterJdbcTemplate.query(query.toString(), params, new RMPerfil());
	}

	@Override
	public List<Perfil> getPerfil(Integer perfil_id) {
		
		StringBuilder query = new StringBuilder(SELECT_PERFIL_QUERY);
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		query.append(" WHERE ");
		query.append("p.perfil_id = :perfil_id");
		params.addValue("perfil_id", perfil_id);

		return this.namedParameterJdbcTemplate.query(query.toString(), params, new RMPerfil());
	}

	@Override
	public Integer createPerfil(Perfil p) {
		
		SimpleJdbcInsert simpleInsert = new SimpleJdbcInsert(this.jdbcTemplate);

		List<String> columnas = new ArrayList<>();
		columnas.add("rol");
		columnas.add("descripcion_perfil");

		simpleInsert.setTableName("perfil");
		simpleInsert.setColumnNames(columnas);

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("rol", p.getRol());
		parametros.put("descripcion_perfil", p.getDescripcion_perfil());
		
		simpleInsert.setGeneratedKeyName("perfil_id");
		Number perfil_id = simpleInsert.executeAndReturnKey(parametros);

		return perfil_id.intValue();
	}
	
}
