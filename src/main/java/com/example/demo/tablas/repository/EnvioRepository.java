package com.example.demo.tablas.repository;

import java.util.List;

import com.example.demo.tablas.model.Envio;

public interface EnvioRepository {

    /* SERVICIOS DE ENVIO */

    // GET
    List<Envio> getTotalidadEnvios();
    Envio getEnvioPorId(Integer envio_id);
    List<Envio> getEnviosPorPedido(Integer pedido_id);

    // POST
    Integer createEnvio(Envio e);

    // PUT
    void updateEnvio(Envio e);

    // DELETE
    void deleteEnvioFisico(Integer envio_id);
}
