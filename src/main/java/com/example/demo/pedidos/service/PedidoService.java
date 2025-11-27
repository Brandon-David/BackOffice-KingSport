package com.example.demo.pedidos.service;

import java.util.List;

import com.example.demo.pedidos.model.Envio;
import com.example.demo.pedidos.model.Pedido;

public interface PedidoService {

	/* SERVICIOS DE PEDIDO */

    // GET
    List<Pedido> getTotalidadPedidos();
    List<Pedido> getTotalidadPedidosCliente();
    
    Pedido getPedidoPorId(Integer pedido_id);
    
    List<Pedido> getPedidosPorCliente(Integer cliente_id);
    
    // POST
    Integer createPedido(Pedido p);

    // PUT
    void updatePedido(Pedido p);

    // DELETE
    void deletePedidoFisico(Integer pedido_id);
    
    /* SERVICIOS DE DETALLES_PEDIDO */
    // GET
    
    // POST
    
    // PUT
    
    // DELETE
    
    
    /* SERVICIOS DE ENVIO */

    // GET
    List<Envio> getTotalidadEnvios();
    
    Envio getEnvioPorId(Integer envio_id);
    
    List<Envio> getEnviosPorPedido(Integer pedido_id);

    // POST
    Integer createEnvio(Envio e);
	
}
