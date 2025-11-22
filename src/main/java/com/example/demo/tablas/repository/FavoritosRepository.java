package com.example.demo.tablas.repository;

import java.util.List;

import com.example.demo.tablas.model.Favoritos;

public interface FavoritosRepository {

    /* SERVICIOS DE FAVORITOS */

    // GET
    List<Favoritos> getTotalidadFavoritos();
    Favoritos getFavoritosPorId(Integer favoritos_id);
    List<Favoritos> getFavoritosPorCliente(Integer cliente_id);

    // POST
    Integer createFavoritos(Favoritos f);

    // DELETE
    void deleteFavoritosFisico(Integer favoritos_id);
}
