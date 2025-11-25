package com.example.demo.departamento.controller;

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
import com.example.demo.departamento.model.Categoria;
import com.example.demo.departamento.model.Departamento;
import com.example.demo.departamento.model.Subcategoria;
import com.example.demo.departamento.service.DepartamentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/departamento") // contexto base
@Tag(name = "Departamento", description = "Endpoints de departamentos, categorías y subcategorías")
@PropertySource("classpath:open-api-descriptions/openapi-departamento.properties")
public class DepartamentoController {

    private static final Logger log = LoggerFactory.getLogger(DepartamentoController.class);

    private final DepartamentoService departamentoService;

    @Autowired
    public DepartamentoController(DepartamentoService departamentoService) {
    	
        this.departamentoService = departamentoService;
    }

    /* CONTROLLER - DEPARTAMENTO */

    @GetMapping("/all")
    @Operation(summary = "Listado de departamentos", description = "${getAllDepartamento.description}")
    public ResponseEntity<CustomResponse<List<Departamento>>> getTotalidadDepartamentos() {

        CustomResponse<List<Departamento>> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            List<Departamento> departamentos = this.departamentoService.getTotalidadDepartamentos();
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(departamentos);
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
    @Operation(summary = "Búsqueda de departamento por id", description = "${getDepartamento.description}")
    public ResponseEntity<CustomResponse<Departamento>> getDepartamento(@RequestParam("departamento_id") Integer departamento_id) {

        CustomResponse<Departamento> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            Departamento departamento = this.departamentoService.getDepartamento(departamento_id);
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(departamento);
        } catch (Exception e) {

            log.trace(e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio fallido");
            rsp.setErrorDescription(e.getMessage());
        }

        return new ResponseEntity<>(rsp, httpStatus);
    }

    @GetMapping("/all/categorias/subcategorias")
    @Operation(summary = "Listado de departamentos, en conjunto a sus categorias y subcategorias correspondientes", description = "${getDepartamentosTree.description}"
    )
    public ResponseEntity<CustomResponse<List<Departamento>>> getTotalidadDepartamentosCatSub() {

        CustomResponse<List<Departamento>> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            List<Departamento> departamentos = this.departamentoService.getTotalidadDepartamentosCatSub();

            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(departamentos);
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
    
    @PostMapping
    @Operation(summary = "Creación de departamento", description = "${postDepartamento.description}")
    public ResponseEntity<CustomResponse<Integer>> createDepartamento(@RequestBody Departamento departamento) {

        CustomResponse<Integer> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.CREATED;

        try {

            Integer departamentoId = this.departamentoService.createDepartamento(departamento);
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(departamentoId);
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
    @Operation(summary = "Actualización de departamento", description = "${putDepartamento.description}")
    public ResponseEntity<CustomResponse<Void>> updateDepartamento(@RequestBody Departamento departamento) {

        CustomResponse<Void> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            this.departamentoService.updateDepartamento(departamento);
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
    @Operation(summary = "Borrado de departamento", description = "${deleteDepartamento.description}")
    public ResponseEntity<CustomResponse<Void>> deleteDepartamento(@RequestParam("departamento_id") Integer departamento_id) {

        CustomResponse<Void> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            this.departamentoService.deleteDepartamentoFisico(departamento_id);
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

    /* CONTROLLER - CATEGORIA */

    @GetMapping("/categoria/all")
    @Operation(summary = "Listado de categorías", description = "${getAllCategoria.description}")
    public ResponseEntity<CustomResponse<List<Categoria>>> getTotalidadCategorias() {

        CustomResponse<List<Categoria>> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            List<Categoria> categorias = this.departamentoService.getTotalidadCategorias();
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(categorias);

        } catch (Exception e) {

            log.trace(e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio fallido");
            rsp.setErrorDescription(e.getMessage());
        }

        return new ResponseEntity<>(rsp, httpStatus);
    }

    @GetMapping("/categoria")
    @Operation(summary = "Búsqueda de categoría por id", description = "${getCategoria.description}")
    public ResponseEntity<CustomResponse<Categoria>> getCategoria(@RequestParam("categoria_id") Integer categoria_id) {

        CustomResponse<Categoria> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            Categoria categoria = this.departamentoService.getCategoria(categoria_id);
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(categoria);
        } catch (Exception e) {

            log.trace(e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio fallido");
            rsp.setErrorDescription(e.getMessage());
        }

        return new ResponseEntity<>(rsp, httpStatus);
    }

    @PostMapping("/categoria")
    @Operation(summary = "Creación de categoría", description = "${postCategoria.description}")
    public ResponseEntity<CustomResponse<Integer>> createCategoria(@RequestBody Categoria categoria) {

        CustomResponse<Integer> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.CREATED;

        try {

            Integer categoriaId = this.departamentoService.createCategoria(categoria);
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(categoriaId);

        } catch (Exception e) {

            log.trace(e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio fallido");
            rsp.setErrorDescription(e.getMessage());
        }

        return new ResponseEntity<>(rsp, httpStatus);
    }

    @PutMapping("/categoria")
    @Operation(summary = "Actualización de categoría", description = "${putCategoria.description}")
    public ResponseEntity<CustomResponse<Void>> updateCategoria(@RequestBody Categoria categoria) {

        CustomResponse<Void> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            this.departamentoService.updateCategoria(categoria);
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");

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

    @DeleteMapping("/categoria")
    @Operation(summary = "Borrado de categoría", description = "${deleteCategoria.description}")
    public ResponseEntity<CustomResponse<Void>> deleteCategoria(@RequestParam("categoria_id") Integer categoria_id) {

        CustomResponse<Void> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            this.departamentoService.deleteCategoriaFisico(categoria_id);
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

    /* CONTROLLER - SUBCATEGORIA */

    @GetMapping("/subcategoria/all")
    @Operation(summary = "Listado de subcategorías", description = "${getAllSubcategoria.description}")
    public ResponseEntity<CustomResponse<List<Subcategoria>>> getTotalidadSubcategorias() {

        CustomResponse<List<Subcategoria>> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            List<Subcategoria> subcategorias = this.departamentoService.getTotalidadSubcategorias();
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(subcategorias);
        } catch (Exception e) {

            log.trace(e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio fallido");
            rsp.setErrorDescription(e.getMessage());
        }

        return new ResponseEntity<>(rsp, httpStatus);
    }

    @GetMapping("/subcategoria")
    @Operation(summary = "Búsqueda de subcategoría por id", description = "${getSubcategoria.description}")
    public ResponseEntity<CustomResponse<Subcategoria>> getSubcategoria(@RequestParam("subcategoria_id") Integer subcategoria_id) {

        CustomResponse<Subcategoria> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            Subcategoria subcategoria = this.departamentoService.getSubcategoria(subcategoria_id);
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(subcategoria);
        } catch (Exception e) {

            log.trace(e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio fallido");
            rsp.setErrorDescription(e.getMessage());
        }

        return new ResponseEntity<>(rsp, httpStatus);
    }

    @PostMapping("/subcategoria")
    @Operation(summary = "Creación de subcategoría", description = "${postSubcategoria.description}")
    public ResponseEntity<CustomResponse<Integer>> createSubcategoria(@RequestBody Subcategoria subcategoria) {

        CustomResponse<Integer> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.CREATED;

        try {

            Integer subcategoriaId = this.departamentoService.createSubcategoria(subcategoria);
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(subcategoriaId);

        } catch (Exception e) {

            log.trace(e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio fallido");
            rsp.setErrorDescription(e.getMessage());
        }

        return new ResponseEntity<>(rsp, httpStatus);
    }

    @PutMapping("/subcategoria")
    @Operation(summary = "Actualización de subcategoría", description = "${putSubcategoria.description}")
    public ResponseEntity<CustomResponse<Void>> updateSubcategoria(@RequestBody Subcategoria subcategoria) {

        CustomResponse<Void> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            this.departamentoService.updateSubcategoria(subcategoria);
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

    @DeleteMapping("/subcategoria")
    @Operation(summary = "Borrado de subcategoría", description = "${deleteSubcategoria.description}")
    public ResponseEntity<CustomResponse<Void>> deleteSubcategoria(@RequestParam("subcategoria_id") Integer subcategoria_id) {

        CustomResponse<Void> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            this.departamentoService.deleteSubcategoriaFisico(subcategoria_id);
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