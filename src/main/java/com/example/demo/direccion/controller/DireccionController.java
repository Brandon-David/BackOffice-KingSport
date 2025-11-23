package com.example.demo.direccion.controller;

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

import com.example.demo.cliente.controller.ClienteController;
import com.example.demo.cliente.service.ClienteService;
import com.example.demo.common.CustomResponse;
import com.example.demo.direccion.model.Direccion;
import com.example.demo.direccion.service.DireccionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/direcciones") 							// Primer contexto que tienen todos los servios de esta clase
@Tag(name = "Direccion", description = "Direccion endpoints") 	// Documentacion
@PropertySource("classpath:open-api-descriptions/openapi-direccion.properties")
public class DireccionController {
	
	private static final Logger log = LoggerFactory.getLogger(DireccionController.class);

    private final DireccionService direccionService;
    
    @Autowired
    public DireccionController(DireccionService direccionService) {
        
    	this.direccionService = direccionService;
    }
	
	@GetMapping("/all")
    @Operation(summary = "Listado de direcciones", description = "${getAllDireccion.description}")
    public ResponseEntity<CustomResponse<List<Direccion>>> getTotalidadDirecciones() {

        CustomResponse<List<Direccion>> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            List<Direccion> direcciones = this.direccionService.getTotalidadDirecciones();
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(direcciones);

        } catch (Exception e) {

            log.trace(e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio fallido");
            rsp.setErrorDescription(e.getMessage());
        }

        return new ResponseEntity<>(rsp, httpStatus);
    }

    @GetMapping
    @Operation(summary = "Búsqueda de dirección por id", description = "${getDireccion.description}")
    public ResponseEntity<CustomResponse<Direccion>> getDireccion(@RequestParam("direccion_id") Integer direccion_id) {

        CustomResponse<Direccion> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            Direccion direccion = this.direccionService.getDireccion(direccion_id);
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(direccion);

        } catch (Exception e) {

            log.trace(e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio fallido");
            rsp.setErrorDescription(e.getMessage());
        }

        return new ResponseEntity<>(rsp, httpStatus);
    }

    @GetMapping("/cliente")
    @Operation(summary = "Listado de direcciones por cliente", description = "${getDireccionesCliente.description}")
    public ResponseEntity<CustomResponse<List<Direccion>>> getDireccionesPorCliente(@RequestParam("cliente_id") Integer cliente_id) {

        CustomResponse<List<Direccion>> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            List<Direccion> direcciones = this.direccionService.getDireccionesPorCliente(cliente_id);
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(direcciones);

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
    @Operation(summary = "Creación de dirección", description = "${postDireccion.description}")
    public ResponseEntity<CustomResponse<Integer>> createDireccion(@RequestBody Direccion direccion) {

        CustomResponse<Integer> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.CREATED;

        try {

            Integer direccionId = this.direccionService.createDireccion(direccion);
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(direccionId);

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
    @Operation(summary = "Actualización de dirección", description = "${putDireccion.description}")
    public ResponseEntity<CustomResponse<Void>> updateDireccion(@RequestBody Direccion direccion) {

        CustomResponse<Void> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            this.direccionService.updateDireccion(direccion);
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
    @Operation(summary = "Borrado de dirección", description = "${deleteDireccion.description}")
    public ResponseEntity<CustomResponse<Void>> deleteDireccion(@RequestParam("direccion_id") Integer direccion_id) {

        CustomResponse<Void> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            this.direccionService.deleteDireccionFisica(direccion_id);
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
