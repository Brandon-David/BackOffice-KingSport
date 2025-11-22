package com.example.demo.tablas.repository;

import java.util.List;

import com.example.demo.tablas.model.Carrito;

public interface CarritoRepository {

	/* SERVICIOS DE CARRITO */

    // GET
    List<Carrito> getTotalidadCarritos();
    Carrito getCarritoPorId(Integer carrito_compra_id);
    List<Carrito> getCarritosPorCliente(Integer cliente_id);

    // POST
    Integer createCarrito(Carrito c);

    // PUT
    void updateCarrito(Carrito c);

    // DELETE
    void deleteCarritoFisico(Integer carrito_compra_id);
	
}
