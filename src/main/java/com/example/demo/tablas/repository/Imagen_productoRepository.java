package com.example.demo.tablas.repository;

import java.util.List;

import com.example.demo.tablas.model.Imagen_producto;

public interface Imagen_productoRepository {

    /* SERVICIOS DE IMAGEN_PRODUCTO */

    // GET
    List<Imagen_producto> getTotalidadImagenes();
    Imagen_producto getImagenPorId(Integer imagen_producto_id);
    List<Imagen_producto> getImagenesPorProducto(Integer producto_id);

    // POST
    Integer createImagen(Imagen_producto img);

    // PUT
    void updateImagen(Imagen_producto img);

    // DELETE
    void deleteImagenFisica(Integer imagen_producto_id);
}
