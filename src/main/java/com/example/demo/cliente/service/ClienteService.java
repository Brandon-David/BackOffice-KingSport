package com.example.demo.cliente.service;

import java.util.List;

import com.example.demo.cliente.model.Cliente;

public interface ClienteService {

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
