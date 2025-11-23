package com.example.demo.cliente.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.cliente.model.Cliente;
import com.example.demo.cliente.repository.ClienteRepository;
import com.example.demo.usuario.service.UsuarioServiceImpl;

@Service
public class ClienteServiceImpl implements ClienteService {

	 private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

	    private ClienteRepository clienteRepository;

	    @Autowired
	    public ClienteServiceImpl(ClienteRepository clienteRepository) {

	        this.clienteRepository = clienteRepository;
	    }

	    /* SERVICIOS - CLIENTE */

	    // GET
	    @Override
	    public List<Cliente> getTotalidadClientes(String estado) {
	    	
	        return this.clienteRepository.getTotalidadClientes(estado);
	    }

	    @Override
	    public Cliente getCliente(Integer cliente_id) {
	        
	    	return this.clienteRepository.getCliente(cliente_id);
	    }

	    // POST
	    @Override
	    public Integer createCliente(Cliente cliente) {
	        
	    	return this.clienteRepository.createCliente(cliente);
	    }

	    // PUT
	    @Override
	    public void updateCliente(Cliente cliente) {
	    	
	        this.clienteRepository.updateCliente(cliente);
	    }

	    @Override
	    public void updateEstadoCliente(Integer cliente_id, String estado) {
	        this.clienteRepository.updateEstadoCliente(cliente_id, estado);
	    }

	    // DELETE
	    @Override
	    public void deleteClienteFisico(Integer cliente_id) {
	        this.clienteRepository.deleteClienteFisico(cliente_id);
	    }
}
