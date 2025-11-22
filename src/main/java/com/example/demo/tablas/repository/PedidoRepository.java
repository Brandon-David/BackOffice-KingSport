package com.example.demo.tablas.repository;

import java.util.List;

import com.example.demo.tablas.model.Pedido;

public interface PedidoRepository {

    /* SERVICIOS DE PEDIDO */

    // GET
    List<Pedido> getTotalidadPedidos();
    Pedido getPedidoPorId(Integer pedido_id);
    List<Pedido> getPedidosPorCliente(Integer cliente_id);

    // POST
    Integer createPedido(Pedido p);

    // PUT
    void updatePedido(Pedido p);

    // DELETE
    void deletePedidoFisico(Integer pedido_id);
}
