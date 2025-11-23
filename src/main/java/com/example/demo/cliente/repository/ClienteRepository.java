package com.example.demo.cliente.repository;

import com.example.demo.cliente.model.Cliente;

import java.util.List;

public interface ClienteRepository {

    /* SERVICIOS DE CLIENTE */

    // GET
    List<Cliente> getTotalidadClientes(String estado);
    
    Cliente getCliente(Integer cliente_id);

    // POST
    Integer createCliente(Cliente cliente);

    // PUT
    void updateCliente(Cliente cliente);
    
    void updateEstadoCliente(Integer cliente_id, String estado);

    // DELETE
    void deleteClienteFisico(Integer cliente_id);

}
