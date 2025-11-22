package com.example.demo.tablas.repository;

import java.util.List;

import com.example.demo.tablas.model.Diseño;

public interface DiseñoRepository {

    /* SERVICIOS DE DISEÑO */

    // GET
    List<Diseño> getTotalidadDiseños();
    Diseño getDiseñoPorId(Integer diseno_id);
    List<Diseño> getDiseñosPorCliente(Integer cliente_id);

    // POST
    Integer createDiseño(Diseño d);

    // PUT
    void updateDiseño(Diseño d);

    // DELETE
    void deleteDiseñoFisico(Integer diseno_id);
}
