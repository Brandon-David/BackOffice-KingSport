package com.example.demo.cliente.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.cliente.model.Cliente;
import com.example.demo.cliente.model.Favoritos;
import com.example.demo.cliente.repository.ClienteRepository;
import com.example.demo.producto.model.Producto;
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
	    
	    @Override
	    public void updateDireccionPredeterminada(Integer cliente_id, Integer direccion_id) {
	    	
	    	this.clienteRepository.updateDireccionPredeterminada(cliente_id, direccion_id);
	    }
	    
	    // DELETE
	    @Override
	    public void deleteClienteFisico(Integer cliente_id) {
	        
	    	this.clienteRepository.deleteClienteFisico(cliente_id);
	    }
	    
	    /* SERVICIOS - FAVORITOS */
		
	    // GET
	    @Override
	    public List<Favoritos> getTotalidadFavoritos() {
	
	        return this.clienteRepository.getTotalidadFavoritos();
	    }
	
	    @Override
	    public Favoritos getFavoritosPorId(Integer favoritos_id) {

	
	        return this.clienteRepository.getFavoritosPorId(favoritos_id);
	    }
	
	    @Override
	    public List<Favoritos> getFavoritosPorCliente(Integer cliente_id) {
	
	        return this.clienteRepository.getFavoritosPorCliente(cliente_id);
	    }
	
	    @Override
	    public List<Producto> getProductosFavoritosPorCliente(Integer cliente_id) {
	
	        return this.clienteRepository.getProductosFavoritosPorCliente(cliente_id);
	    }
	
	    // POST
	    @Override
	    public Integer createFavoritos(Integer cliente_id, Integer producto_id) {
	
	        return this.clienteRepository.createFavoritos(cliente_id, producto_id);
	    }
	    
	    @Override
	    public void insertFavoritosCarrito(Integer cliente_id) {
	    	
	    	List<Producto> productos = clienteRepository.getProductosFavoritosPorCliente(cliente_id);
	    	this.clienteRepository.createCarritoPorFavoritos(cliente_id, productos);
	    }
	
	    // DELETE
	    @Override
	    public void deleteFavoritosFisico(Integer cliente_id, Integer producto_id) {
	
	        this.clienteRepository.deleteFavoritosFisico(cliente_id, producto_id);
	    }
	    
}
