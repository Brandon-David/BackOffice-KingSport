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
	
	private final String UPDATE_USUARIO_QUERY = "UPDATE usuario u";
	
	private final String DELETE_USUARIO_QUERY = "DELETE FROM usuario u";
	
	//--> CONSULTAS - PERFIL
	
	private final String SELECT_PERFIL_QUERY = "SELECT p.* FROM perfil p";
	
	@Autowired
	public UsuarioRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate npJdbcTemplate) {
		
		this.jdbcTemplate = jdbcTemplate;
		this.namedParameterJdbcTemplate = npJdbcTemplate;
	}

	/*REPOSITORIO - USUARIO*/
	
	@Override
	public List<Usuario> getTotalidadUsuarios() {
		
		
		return null;
	}
	
	
	@Override
	public Usuario getUsuario(Integer usuario_id) {
		
		StringBuilder query = new StringBuilder(SELECT_USUARIO_QUERY);
		MapSqlParameterSource params = new MapSqlParameterSour;
		
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

		query.append("estado_usuario = :estado_usuario, ");
		params.addValue("estado_usuario", us.getEstado_usuario());

		query.append(" WHERE ");
		query.append("usuario_id = :usuario_id");
		params.addValue("usuario_id", us.getUsuario_id());

		this.namedParameterJdbcTemplate.update(query.toString(), params);

	}

	@Override
	public void updateEstadoUsuario(Integer usuario_id, String estado) {
		
		StringBuilder query = new StringBuilder(UPDATE_USUARIO_QUERY);
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		query.append(" SET ");
		query.append("estado_usuario = :estado_usuario, ");
		params.addValue("estado_usuario", estado);

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
	public List<Usuario> getTotalidadPerfiles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> getPerfil() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer createPerfil(Perfil p) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
