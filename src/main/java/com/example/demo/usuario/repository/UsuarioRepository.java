package com.example.demo.usuario.repository;

import java.util.List;

import com.example.demo.usuario.model.Perfil;
import com.example.demo.usuario.model.Usuario;


public interface UsuarioRepository {

	/* SERVICIOS DE USUARIO */
	//GET
	List<Usuario> getTotalidadUsuarios();
	Usuario getUsuario(Integer usuario_id);
	
	//POST
	Integer createUsuario(Usuario us);
	void updateEstadoUsuario(Integer usuario_id, String estado);
	
	//PUT
	void updateUsuario(Usuario us);
	
	//DELETE
	void deleteUsuarioFisico(Integer usuario_id);
	
	/* SERVICIOS DE PERFIL*/
	//GET
	List<Usuario> getTotalidadPerfiles();
	List<Usuario> getPerfil(Integer usuario_id);
	
	//POST
	Integer createPerfil(Perfil p);
}
