package com.example.demo.usuario.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.CustomResponse;
import com.example.demo.usuario.model.Perfil;
import com.example.demo.usuario.model.Usuario;
import com.example.demo.usuario.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuario") 								// Primer contexto que tienen todos los servios de esta clase
@Tag(name = "Usuario", description = "Usuario endpoints") 	// Documentacion
@PropertySource("classpath:open-api-descriptions/openapi-usuario.properties") 	// Crea la descripcion de cada servicio a
																				// utilizar
public class UsuarioController {

	private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

	private UsuarioService usuarioService;

	@Autowired
	UsuarioController(UsuarioService usuarioService) {
		
		this.usuarioService = usuarioService;// Inyeccion de dependencias
	}
	
	/* CONTROLLER - USUARIO */
	
	@GetMapping("/all")
	@Operation(summary = "Listado de usuarios y su perfil", description = "${getAllUsuario.description}")
	public ResponseEntity<CustomResponse<List<Usuario>>> getTotalidadUsuarios(@RequestParam("estado") String estado) {
		CustomResponse<List<Usuario>> rsp = new CustomResponse<List<Usuario>>();
		HttpStatus httpStatus = HttpStatus.OK;

		try {
			List<Usuario> usuarios = this.usuarioService.getTotalidadUsuarios(estado);
			rsp.setStatus(String.valueOf(httpStatus.value()));
			rsp.setMessage("Servicio exitoso");
			rsp.setData(usuarios);
		} catch (Exception e) {
			
			e.printStackTrace();
			log.trace(e.getMessage());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			rsp.setStatus(String.valueOf(httpStatus.value()));
			rsp.setMessage("Servicio fallido");
			rsp.setErrorDescription(e.getMessage());
		}

		return new ResponseEntity<CustomResponse<List<Usuario>>>(rsp, httpStatus);
	}
	
	@GetMapping
	@Operation(summary = "Busqueda de usuario por id", description = "${getUsuario.description}")
	public ResponseEntity<CustomResponse<List<Usuario>>> getUsuario(@RequestParam("usuario_id") Integer usuario_id) {
		
		CustomResponse<List<Usuario>> rsp = new CustomResponse<List<Usuario>>();
		HttpStatus httpStatus = HttpStatus.OK;

		try {
			List<Usuario> usuario = this.usuarioService.getUsuario(usuario_id);
			rsp.setStatus(String.valueOf(httpStatus.value()));
			rsp.setMessage("Servicio exitoso");
			rsp.setData(usuario);
		} catch (Exception e) {
			log.trace(e.getMessage());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			rsp.setStatus(String.valueOf(httpStatus.value()));
			rsp.setMessage("Servicio fallido");
			rsp.setErrorDescription(e.getMessage());
		}

		return new ResponseEntity<CustomResponse<List<Usuario>>>(rsp, httpStatus);
	}
	
	@PostMapping
	@Operation(summary = "Creacion de usuario", description = "${postUsuario.description}")
	public ResponseEntity<CustomResponse<Integer>> createUsuario(@RequestBody Usuario usuario) {
		
		CustomResponse<Integer> rsp = new CustomResponse<Integer>();	// instancia de custom response para regresar los
																		// datos
		HttpStatus httpStatus = HttpStatus.CREATED;

		try {
			
			Integer usuarioId = this.usuarioService.createUsuario(usuario);
			rsp.setStatus(String.valueOf(httpStatus.value()));
			rsp.setMessage("Servicio exitoso");
			rsp.setData(usuarioId);
		} catch (Exception e) {
			
			log.trace(e.getMessage());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			rsp.setStatus(String.valueOf(httpStatus.value()));
			rsp.setMessage("Servicio fallido");
			rsp.setErrorDescription(e.getMessage());
		}

		return new ResponseEntity<CustomResponse<Integer>>(rsp, httpStatus);
	}
	
	@PutMapping
	@Operation(summary = "Actualizacion de usuario", description = "${postUsuario.description}")
	public ResponseEntity<CustomResponse<Void>> updateUsuario(@RequestBody Usuario usuario) {
		
		CustomResponse<Void> rsp = new CustomResponse<Void>();
		HttpStatus httpStatus = HttpStatus.OK;

		try {
			
			this.usuarioService.updateUsuario(usuario);
			rsp.setStatus(String.valueOf(httpStatus.value()));
			rsp.setMessage("Servicio exitoso");
		} catch (Exception e) {
			
			log.trace(e.getMessage());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			rsp.setStatus(String.valueOf(httpStatus.value()));
			rsp.setMessage("Servicio fallido");
			rsp.setErrorDescription(e.getMessage());
		}

		return new ResponseEntity<CustomResponse<Void>>(rsp, httpStatus);
	}
	
	@PutMapping("/estado")
	@Operation(summary = "Actualizacion del estado de un usuario", description = "${postUsuarioEstado.descripcion}")
	public ResponseEntity<CustomResponse<Void>> updateEstadoUsuario(@RequestParam("usuario_id") Integer usuario_id, @RequestParam("estado") String estado) {
		
		CustomResponse<Void> rsp = new CustomResponse<Void>();
		HttpStatus httpStatus = HttpStatus.OK;

		try {
			
			this.usuarioService.updateEstadoUsuario(usuario_id, estado);
			rsp.setStatus(String.valueOf(httpStatus.value()));
			rsp.setMessage("Servicio exitoso");
		} catch (Exception e) {
			
			log.trace(e.getMessage());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			rsp.setStatus(String.valueOf(httpStatus.value()));
			rsp.setMessage("Servicio fallido");
			rsp.setErrorDescription(e.getMessage());
		}

		return new ResponseEntity<CustomResponse<Void>>(rsp, httpStatus);
	}
	
	@PutMapping("/perfil")
	@Operation(summary = "Actualizacion del perfil o rol de un usuario", description = "${putUsuarioEstado.description}")
	public ResponseEntity<CustomResponse<Void>> updateUsuarioPerfil(@RequestParam("usuario_id") Integer usuario_id, @RequestParam("perfil_id") Integer perfil_id) {
		
		CustomResponse<Void> rsp = new CustomResponse<Void>();
		HttpStatus httpStatus = HttpStatus.OK;

		try {
			
			this.usuarioService.updateUsuarioPerfil(usuario_id, perfil_id);
			rsp.setStatus(String.valueOf(httpStatus.value()));
			rsp.setMessage("Servicio exitoso");
		} catch (Exception e) {
			
			log.trace(e.getMessage());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			rsp.setStatus(String.valueOf(httpStatus.value()));
			rsp.setMessage("Servicio fallido");
			rsp.setErrorDescription(e.getMessage());
		}

		return new ResponseEntity<CustomResponse<Void>>(rsp, httpStatus);
	}

	@DeleteMapping
	@Operation(summary = "Borrado de usuario", description = "${deleteUsuario.description}")
	public ResponseEntity<CustomResponse<Void>> deleteUser(@RequestParam("usuario_id") Integer usuario_id) {
		CustomResponse<Void> rsp = new CustomResponse<Void>();
		HttpStatus httpStatus = HttpStatus.OK;

		try {
			this.usuarioService.deleteUsuarioFisico(usuario_id);
			rsp.setStatus(String.valueOf(httpStatus.value()));
			rsp.setMessage("Servicio exitoso");
		} catch (Exception e) {
			log.trace(e.getMessage());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			rsp.setStatus(String.valueOf(httpStatus.value()));
			rsp.setMessage("Servicio fallido");
			rsp.setErrorDescription(e.getMessage());
		}

		return new ResponseEntity<CustomResponse<Void>>(rsp, httpStatus);
	}
	
	/* CONTROLLER - PERFIL */
	
	@GetMapping("/perfil/all")
	@Operation(summary = "Listado de perfiles", description = "${getAllPerfil.description}")
	public ResponseEntity<CustomResponse<List<Perfil>>> getTotalidadPerfiles(@RequestParam("estado") String estado) {
		CustomResponse<List<Perfil>> rsp = new CustomResponse<List<Perfil>>();
		HttpStatus httpStatus = HttpStatus.OK;

		try {
			
			List<Perfil> perfiles = this.usuarioService.getTotalidadPerfiles(estado);
			rsp.setStatus(String.valueOf(httpStatus.value()));
			rsp.setMessage("Servicio exitoso");
			rsp.setData(perfiles);
		} catch (Exception e) {
			
			log.trace(e.getMessage());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			rsp.setStatus(String.valueOf(httpStatus.value()));
			rsp.setMessage("Servicio fallido");
			rsp.setErrorDescription(e.getMessage());
		}

		return new ResponseEntity<CustomResponse<List<Perfil>>>(rsp, httpStatus);
	}
	
	
	@GetMapping("/perfil")
	@Operation(summary = "Listado de perfiles", description = "${getPerfil.description}")
	public ResponseEntity<CustomResponse<List<Perfil>>> getPerfil(@RequestParam("perfil_id") Integer perfil_id) {
		CustomResponse<List<Perfil>> rsp = new CustomResponse<List<Perfil>>();
		HttpStatus httpStatus = HttpStatus.OK;

		try {
			
			List<Perfil> perfil = this.usuarioService.getPerfil(perfil_id);
			rsp.setStatus(String.valueOf(httpStatus.value()));
			rsp.setMessage("Servicio exitoso");
			rsp.setData(perfil);
		} catch (Exception e) {
			
			log.trace(e.getMessage());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			rsp.setStatus(String.valueOf(httpStatus.value()));
			rsp.setMessage("Servicio fallido");
			rsp.setErrorDescription(e.getMessage());
		}

		return new ResponseEntity<CustomResponse<List<Perfil>>>(rsp, httpStatus);
	}
	
	@PostMapping("/perfil")
	@Operation(summary = "Creacion de perfil", description = "${postPerfil.description}")
	public ResponseEntity<CustomResponse<Integer>> createUsuario(@RequestBody Perfil perfil) {
		
		CustomResponse<Integer> rsp = new CustomResponse<Integer>();
		HttpStatus httpStatus = HttpStatus.CREATED;

		try {
			
			Integer perfilId = this.usuarioService.createPerfil(perfil);
			rsp.setStatus(String.valueOf(httpStatus.value()));
			rsp.setMessage("Servicio exitoso");
			rsp.setData(perfilId);
		} catch (Exception e) {
			
			log.trace(e.getMessage());
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			rsp.setStatus(String.valueOf(httpStatus.value()));
			rsp.setMessage("Servicio fallido");
			rsp.setErrorDescription(e.getMessage());
		}

		return new ResponseEntity<CustomResponse<Integer>>(rsp, httpStatus);
	}
	
	
}
