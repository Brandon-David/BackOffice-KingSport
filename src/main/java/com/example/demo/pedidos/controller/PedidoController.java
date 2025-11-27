package com.example.demo.pedidos.controller;

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
import com.example.demo.pedidos.model.Pedido;
import com.example.demo.pedidos.service.PedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pedido")   // Contexto base para todos los servicios de pedido
@Tag(name = "Pedido", description = "Pedido endpoints")
@PropertySource("classpath:open-api-descriptions/openapi-pedido.properties")
public class PedidoController {

    private static final Logger log = LoggerFactory.getLogger(PedidoController.class);

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        
    	this.pedidoService = pedidoService;
    }

    /* CONTROLLER - PEDIDO */

    @GetMapping("/all")
    @Operation(summary = "Listado de todos los pedidos", description = "${getAllPedido.description}")
    public ResponseEntity<CustomResponse<List<Pedido>>> getTotalidadPedidos() {

        CustomResponse<List<Pedido>> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            List<Pedido> pedidos = this.pedidoService.getTotalidadPedidos();
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(pedidos);

        } catch (Exception e) {

            log.trace(e.getMessage());
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio fallido");
            rsp.setErrorDescription(e.getMessage());
        }

        return new ResponseEntity<>(rsp, httpStatus);
    }

    @GetMapping("all/cliente/")
    @Operation(summary = "Listado de pedidos con información de cliente", description = "${getAllPedidoCliente.description}")
    public ResponseEntity<CustomResponse<List<Pedido>>> getTotalidadPedidosCliente() {

        CustomResponse<List<Pedido>> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            List<Pedido> pedidos = this.pedidoService.getTotalidadPedidosCliente();
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(pedidos);

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
    @Operation(summary = "Búsqueda de pedido por id", description = "${getPedido.description}")
    public ResponseEntity<CustomResponse<Pedido>> getPedidoPorId(@RequestParam("pedido_id") Integer pedido_id) {

        CustomResponse<Pedido> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            Pedido pedido = this.pedidoService.getPedidoPorId(pedido_id);
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(pedido);

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
    @Operation(summary = "Listado de pedidos por cliente", description = "${getPedidosPorCliente.description}")
    public ResponseEntity<CustomResponse<List<Pedido>>> getPedidosPorCliente(@RequestParam("cliente_id") Integer cliente_id) {

        CustomResponse<List<Pedido>> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            List<Pedido> pedidos = this.pedidoService.getPedidosPorCliente(cliente_id);
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(pedidos);

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
    @Operation(summary = "Creación de pedido", description = "${postPedido.description}")
    public ResponseEntity<CustomResponse<Integer>> createPedido(@RequestBody Pedido pedido) {

        CustomResponse<Integer> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.CREATED;

        try {

            Integer pedidoId = this.pedidoService.createPedido(pedido);
            rsp.setStatus(String.valueOf(httpStatus.value()));
            rsp.setMessage("Servicio exitoso");
            rsp.setData(pedidoId);

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
    @Operation(summary = "Actualización de pedido", description = "${putPedido.description}")
    public ResponseEntity<CustomResponse<Void>> updatePedido(@RequestBody Pedido pedido) {

        CustomResponse<Void> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            this.pedidoService.updatePedido(pedido);
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
    @Operation(summary = "Borrado de pedido", description = "${deletePedido.description}")
    public ResponseEntity<CustomResponse<Void>> deletePedido(@RequestParam("pedido_id") Integer pedido_id) {

        CustomResponse<Void> rsp = new CustomResponse<>();
        HttpStatus httpStatus = HttpStatus.OK;

        try {

            this.pedidoService.deletePedidoFisico(pedido_id);
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
