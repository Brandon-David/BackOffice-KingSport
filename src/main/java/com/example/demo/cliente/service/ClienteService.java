package com.example.demo.cliente.service;

import java.util.List;

import com.example.demo.cliente.model.Cliente;
import com.example.demo.cliente.model.Favoritos;
import com.example.demo.producto.model.Producto;

public interface ClienteService {

	// GET
    List<Cliente> getTotalidadClientes(String estado);
    
    Cliente getCliente(Integer cliente_id);

    // POST
    Integer createCliente(Cliente cliente);

    // PUT
    void updateCliente(Cliente cliente);
    
    void updateEstadoCliente(Integer cliente_id, String estado);
    
    void updateDireccionPredeterminada(Integer cliente_id, Integer direccion_id);

    // DELETE
    void deleteClienteFisico(Integer cliente_id);
    
    /* SERVICIOS DE FAVORITOS */

    // GET
    List<Favoritos> getTotalidadFavoritos();
    
    Favoritos getFavoritosPorId(Integer favoritos_id);
    
    List<Favoritos> getFavoritosPorCliente(Integer cliente_id);
    
    List<Producto> getProductosFavoritosPorCliente(Integer cliente_id);

    // POST
    Integer createFavoritos(Favoritos f);

    // DELETE
    void deleteFavoritosFisico(Integer cliente_id, Integer producto_id);

}
