package com.example.demo.usuario.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.usuario.model.Perfil;
import com.example.demo.usuario.model.Usuario;

public class RMUsuarioPerfil implements RowMapper<Usuario>{

	 private final RMUsuario rmUsuario = new RMUsuario();
	 private final RMPerfil rmPerfil = new RMPerfil();
	 
	@Override
	public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Usuario usuario = rmUsuario.mapUsuario(rs, rowNum, "u_");
        Perfil perfil = rmPerfil.mapPerfil(rs, rowNum, "p_");
        
        usuario.setPerfil(perfil);
        return usuario;
	}

}
