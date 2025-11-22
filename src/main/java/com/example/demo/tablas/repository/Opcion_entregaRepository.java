package com.example.demo.tablas.repository;

import java.util.List;

import com.example.demo.tablas.model.Opcion_entrega;

public interface Opcion_entregaRepository {

    /* SERVICIOS DE OPCION_ENTREGA */

    // GET
    List<Opcion_entrega> getTotalidadOpcionesEntrega();
    Opcion_entrega getOpcionEntregaPorId(Integer opcion_entrega_id);

    // POST
    Integer createOpcionEntrega(Opcion_entrega o);

    // PUT
    void updateOpcionEntrega(Opcion_entrega o);

    // DELETE
    void deleteOpcionEntregaFisica(Integer opcion_entrega_id);
}
