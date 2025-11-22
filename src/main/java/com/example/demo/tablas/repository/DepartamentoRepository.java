package com.example.demo.tablas.repository;

import java.util.List;

import com.example.demo.tablas.model.Departamento;

public interface DepartamentoRepository {

    /* SERVICIOS DE DEPARTAMENTO */

    // GET
    List<Departamento> getTotalidadDepartamentos();
    Departamento getDepartamentoPorId(Integer departamento_id);

    // POST
    Integer createDepartamento(Departamento d);

    // PUT
    void updateDepartamento(Departamento d);

    // DELETE
    void deleteDepartamentoFisico(Integer departamento_id);
}
