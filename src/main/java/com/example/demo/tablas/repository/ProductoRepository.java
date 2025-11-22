package com.example.demo.tablas.repository;

import java.util.List;

import com.example.demo.tablas.model.Producto;

public interface ProductoRepository {

    /* SERVICIOS DE PRODUCTO */

    // GET
    List<Producto> getTotalidadProductos();
    Producto getProductoPorId(Integer producto_id);
    List<Producto> getProductosPorCategoria(Integer categoria_id);

    // POST
    Integer createProducto(Producto p);

    // PUT
    void updateProducto(Producto p);

    // DELETE
    void deleteProductoFisico(Integer producto_id);
}
