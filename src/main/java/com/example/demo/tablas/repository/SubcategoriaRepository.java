package com.example.demo.tablas.repository;

import java.util.List;

import com.example.demo.tablas.model.Subcategoria;

public interface SubcategoriaRepository {

    /* SERVICIOS DE SUBCATEGORIA */

    // GET
    List<Subcategoria> getTotalidadSubcategorias();
    Subcategoria getSubcategoriaPorId(Integer subcategoria_id);

    // POST
    Integer createSubcategoria(Subcategoria s);

    // PUT
    void updateSubcategoria(Subcategoria s);

    // DELETE
    void deleteSubcategoriaFisico(Integer subcategoria_id);
}
