package com.example.demo.diseño.controller;

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
import com.example.demo.diseño.model.Diseño;
import com.example.demo.diseño.service.DiseñoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/diseno") 														// Primer contexto que tienen todos los servicios de esta clase
@Tag(name = "Diseño", description = "Diseño endpoints") 						// Documentación
@PropertySource("classpath:open-api-descriptions/openapi-diseno.properties")
public class DiseñoController {

	private static final Logger log = LoggerFactory.getLogger(DiseñoController.class);

    private final DiseñoService diseñoService;

    @Autowired
    public DiseñoController(DiseñoService diseñoService) {
    	
        this.diseñoService = diseñoService;
    }

    /* CONTROLLER - DISEÑO */

    @GetMapping("/all")
    @Operation(summary = "Listado de diseños", description = "${getAllDiseno.description}")
    public ResponseEntity<CustomResponse<List<Diseño>>> getTotalidadDiseños(@RequestParam("estado") String estado) {

        CustomResponse<List<Diseño>> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            List<Diseño> disenos = this.diseñoService.getTotalidadDiseños(estado);
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(disenos);
        } catch (Exception e) {

        	e.printStackTrace();
            log.trace(e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio fallido");
            rsp.setErrorDescription(e.getMessage());
        }

        return new ResponseEntity<>(rsp, httpStatus);
    }

    @GetMapping
    @Operation(summary = "Búsqueda de diseño por id", description = "${getDiseno.description}")
    public ResponseEntity<CustomResponse<Diseño>> getDiseño(
            @RequestParam("diseno_id") Integer diseno_id) {

        CustomResponse<Diseño> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            Diseño diseno = this.diseñoService.getDiseño(diseno_id);
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(diseno);
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
    @Operation(summary = "Listado de diseños por cliente", description = "${getDisenosCliente.description}")
    public ResponseEntity<CustomResponse<List<Diseño>>> getDiseñosPorCliente(@RequestParam("cliente_id") Integer cliente_id) {

        CustomResponse<List<Diseño>> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            List<Diseño> disenos = this.diseñoService.getDiseñosPorCliente(cliente_id);
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(disenos);
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
    @Operation(summary = "Creación de diseño", description = "${postDiseno.description}")
    public ResponseEntity<CustomResponse<Integer>> createDiseño(@RequestBody Diseño diseno) {

        CustomResponse<Integer> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.CREATED;

        try {

            Integer disenoId = this.diseñoService.createDiseño(diseno);
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(disenoId);
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
    @Operation(summary = "Actualización de diseño", description = "${putDiseno.description}")
    public ResponseEntity<CustomResponse<Void>> updateDiseño(@RequestBody Diseño diseno) {

        CustomResponse<Void> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            this.diseñoService.updateDiseño(diseno);
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
    @Operation(summary = "Actualización del estado de un diseño", description = "${putDisenoEstado.description}")
    public ResponseEntity<CustomResponse<Void>> updateEstadoDiseño(@RequestParam("diseno_id") Integer diseno_id, @RequestParam("estado") String estado) {

        CustomResponse<Void> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            this.diseñoService.updateEstadoDiseño(diseno_id, estado);
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
    @Operation(summary = "Borrado de diseño", description = "${deleteDiseno.description}")
    public ResponseEntity<CustomResponse<Void>> deleteDiseño(@RequestParam("diseno_id") Integer diseno_id) {

        CustomResponse<Void> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            this.diseñoService.deleteDiseñoFisico(diseno_id);
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
