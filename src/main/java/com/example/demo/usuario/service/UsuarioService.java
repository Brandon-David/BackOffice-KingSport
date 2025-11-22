package com.example.demo.usuario.service;

import java.util.List;

import com.example.demo.usuario.model.Perfil;
import com.example.demo.usuario.model.Usuario;

public interface UsuarioService {

	/* SERVICIOS DE USUARIO */
	
		//GET
	List<Usuario> getTotalidadUsuarios(String estado);
	
	List<Usuario> getUsuario(Integer usuario_id);
	
		//POST
	Integer createUsuario(Usuario us);
	
		//PUT
	void updateUsuario(Usuario us);
	
	void updateEstadoUsuario(Integer usuario_id, String estado);
	
	void updateUsuarioPerfil(Integer usuario_id, Integer perfil_id);
	
		//DELETE
	void deleteUsuarioFisico(Integer usuario_id);
	
	/* SERVICIOS DE PERFIL */
	
		//GET
	List<Perfil> getTotalidadPerfiles(String estado);
	
	List<Perfil> getPerfil(Integer perfil_id);
	
		//POST
	Integer createPerfil(Perfil p);
	
}
