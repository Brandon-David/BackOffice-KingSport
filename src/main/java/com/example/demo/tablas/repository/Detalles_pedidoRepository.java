package com.example.demo.tablas.repository;

import java.util.List;

import com.example.demo.tablas.model.Detalles_pedido;

public interface Detalles_pedidoRepository {

    /* SERVICIOS DE DETALLES_PEDIDO */

    // GET
    List<Detalles_pedido> getTotalidadDetalles();
    Detalles_pedido getDetallePorId(Integer detalle_pedido_id);
    List<Detalles_pedido> getDetallesPorPedido(Integer pedido_id);

    // POST
    Integer createDetalle(Detalles_pedido d);

    // PUT
    void updateDetalle(Detalles_pedido d);

    // DELETE
    void deleteDetalleFisico(Integer detalle_pedido_id);
}
