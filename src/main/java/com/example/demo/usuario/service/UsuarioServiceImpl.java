package com.example.demo.usuario.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.usuario.model.Perfil;
import com.example.demo.usuario.model.Usuario;
import com.example.demo.usuario.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	private UsuarioRepository usuarioRepository;

	@Autowired
	UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		
		this.usuarioRepository = usuarioRepository;// Inyeccion de dependencias
	}
	
	/* SERVICIOS - USUARIO */
	
	@Override
	public List<Usuario> getTotalidadUsuarios(String estado) {
		
		return this.usuarioRepository.getTotalidadUsuarios(estado);
	}

	@Override
	public List<Usuario> getUsuario(Integer usuario_id) {
		
		return this.usuarioRepository.getUsuario(usuario_id);
	}

	@Override
	@Transactional
	public Integer createUsuario(Usuario us) {
		
		Integer usuario_id = this.usuarioRepository.createUsuario(us);
		Integer perfil_id = us.getPerfil().getPerfil_id();
		
		this.usuarioRepository.createUsuarioPerfil(usuario_id, perfil_id);

		return usuario_id;	
	}

	@Override
	public void updateEstadoUsuario(Integer usuario_id, String estado) {
		
		this.usuarioRepository.updateEstadoUsuario(usuario_id, estado);
	}
	
	@Override
	public void updateUsuarioPerfil(Integer usuario_id, Integer perfil_id) {
		
		this.usuarioRepository.updateUsuarioPerfil(usuario_id, perfil_id);
	}

	@Override
	public void updateUsuario(Usuario us) {
		
		this.usuarioRepository.updateUsuario(us);
	}

	@Override
	public void deleteUsuarioFisico(Integer usuario_id) {
		
		this.usuarioRepository.deleteUsuarioFisico(usuario_id);
	}
	
	/* SERVICIOS - PERFIL */

	@Override
	public List<Perfil> getTotalidadPerfiles(String estado) {
		
		return this.usuarioRepository.getTotalidadPerfiles(estado);
	}

	@Override
	public List<Perfil> getPerfil(Integer perfil_id) {
	
		return this.usuarioRepository.getPerfil(perfil_id);
	}

	@Override
	public Integer createPerfil(Perfil p) {
		
		return this.usuarioRepository.createPerfil(p);
	}

}
