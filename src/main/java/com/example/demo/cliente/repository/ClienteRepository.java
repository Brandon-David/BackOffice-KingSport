package com.example.demo.cliente.repository;

import com.example.demo.cliente.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {

    /* SERVICIOS DE CLIENTE */

    // GET
    List<Cliente> getTotalidadClientes();
    Cliente getCliente(Integer cliente_id);

    // POST
    Integer createCliente(Cliente cliente);

    // PUT
    void updateCliente(Cliente cliente);

    // DELETE
    void deleteClienteFisico(Integer cliente_id);

	
}
