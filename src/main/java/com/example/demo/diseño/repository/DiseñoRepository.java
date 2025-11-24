package com.example.demo.diseño.repository;

import java.util.List;

import com.example.demo.diseño.model.Diseño;

public interface DiseñoRepository {

    /* REPOSITORIO DE DISEÑO */

    // GET
    List<Diseño> getTotalidadDiseños(String estado);
    
    Diseño getDiseño(Integer diseno_id);
    
    List<Diseño> getDiseñosPorCliente(Integer cliente_id);

    // POST
    Integer createDiseño(Diseño d);

    // PUT
    void updateDiseño(Diseño d);
    
    void updateEstadoDiseño(Integer diseno_id, String estado);

    // DELETE
    void deleteDiseñoFisico(Integer diseno_id);
}
