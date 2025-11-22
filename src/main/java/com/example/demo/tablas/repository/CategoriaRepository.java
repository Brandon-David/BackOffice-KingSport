package com.example.demo.tablas.repository;

import java.util.List;

import com.example.demo.tablas.model.Categoria;

public interface CategoriaRepository {

    /* SERVICIOS DE CATEGORIA */

    // GET
    List<Categoria> getTotalidadCategorias();
    Categoria getCategoriaPorId(Integer categoria_id);

    // POST
    Integer createCategoria(Categoria c);

    // PUT
    void updateCategoria(Categoria c);

    // DELETE
    void deleteCategoriaFisico(Integer categoria_id);
}
