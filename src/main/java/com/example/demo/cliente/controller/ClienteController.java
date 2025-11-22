package com.example.demo.cliente.controller;

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

import com.example.demo.cliente.model.Cliente;
import com.example.demo.cliente.service.ClienteService;
import com.example.demo.common.CustomResponse;
import com.example.demo.usuario.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/cliente") 								// Primer contexto que tienen todos los servios de esta clase
@Tag(name = "Cliente", description = "Cliente endpoints") 	// Documentacion
@PropertySource("classpath:open-api-descriptions/openapi-cliente.properties") 	// Crea la descripcion de cada servicio a
	
public class ClienteController {

    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

    private final ClienteService clienteService;
    
    @Autowired
    public ClienteController(ClienteService clienteService) {
        
    	this.clienteService = clienteService;
    }
    
    /* CONTROLLER - CLIENTE */

    @GetMapping("/all")
    @Operation(summary = "Listado de clientes", description = "${getAllCliente.description}")
    public ResponseEntity<CustomResponse<List<Cliente>>> getTotalidadClientes() {

        CustomResponse<List<Cliente>> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {
        	
            List<Cliente> clientes = this.clienteService.getTotalidadClientes();
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(clientes);
        } catch (Exception e) {
        	
            // e.printStackTrace();
            log.trace(e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio fallido");
            rsp.setErrorDescription(e.getMessage());
        }

        return new ResponseEntity<>(rsp, httpStatus);
    }

    @GetMapping
    @Operation(summary = "Búsqueda de cliente por id", description = "${getCliente.description}")
    public ResponseEntity<CustomResponse<Cliente>> getCliente(@RequestParam("cliente_id") Integer cliente_id) {

        CustomResponse<Cliente> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {
        	
            Cliente cliente = this.clienteService.getCliente(cliente_id);
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(cliente);

        } catch (Exception e) {
            log.trace(e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio fallido");
            rsp.setErrorDescription(e.getMessage());
        }

        return new ResponseEntity<>(rsp, httpStatus);
    }

    @PostMapping
    @Operation(summary = "Creación de cliente", description = "${postCliente.description}")
    public ResponseEntity<CustomResponse<Integer>> createCliente(@RequestBody Cliente cliente) {

        CustomResponse<Integer> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.CREATED;

        try {
        	
            Integer clienteId = this.clienteService.createCliente(cliente);
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(clienteId);
        } catch (Exception e) {
        	
            log.trace(e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio fallido");
            rsp.setErrorDescription(e.getMessage());
        }

        return new ResponseEntity<>(rsp, httpStatus);
    }

    @PutMapping
    @Operation(summary = "Actualización de cliente", description = "${putCliente.description}")
    public ResponseEntity<CustomResponse<Void>> updateCliente(@RequestBody Cliente cliente) {

        CustomResponse<Void> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {
        	
            this.clienteService.updateCliente(cliente);
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
        } catch (Exception e) {
        	
            log.trace(e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio fallido");
            rsp.setErrorDescription(e.getMessage());
        }

        return new ResponseEntity<>(rsp, httpStatus);
    }

    @PutMapping("/estado")
    @Operation(summary = "Actualización del estado de un cliente", description = "${putClienteEstado.description}")
    public ResponseEntity<CustomResponse<Void>> updateEstadoCliente(@RequestParam("cliente_id") Integer cliente_id, @RequestParam("estado") String estado) {

        CustomResponse<Void> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {
        	
            this.clienteService.updateEstadoCliente(cliente_id, estado);
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
        } catch (Exception e) {
        	
            log.trace(e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio fallido");
            rsp.setErrorDescription(e.getMessage());
        }

        return new ResponseEntity<>(rsp, httpStatus);
    }

    @DeleteMapping
    @Operation(summary = "Borrado de cliente", description = "${deleteCliente.description}")
    public ResponseEntity<CustomResponse<Void>> deleteCliente(@RequestParam("cliente_id") Integer cliente_id) {

        CustomResponse<Void> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {
        	
            this.clienteService.deleteClienteFisico(cliente_id);
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
        } catch (Exception e) {
        	
            log.trace(e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio fallido");
            rsp.setErrorDescription(e.getMessage());
        }

        return new ResponseEntity<>(rsp, httpStatus);
    }
    
}
