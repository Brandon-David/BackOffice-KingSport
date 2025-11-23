package com.example.demo.direccion.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.cliente.repository.ClienteRepository;
import com.example.demo.cliente.service.ClienteServiceImpl;
import com.example.demo.direccion.model.Direccion;
import com.example.demo.direccion.repository.DireccionRepository;

@Service
public class DireccionServiceImpl implements DireccionService{

	private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    private final ClienteRepository clienteRepository;
    private final DireccionRepository direccionRepository;

    @Autowired
    public DireccionServiceImpl(ClienteRepository clienteRepository, DireccionRepository direccionRepository) {

        this.clienteRepository = clienteRepository;
        this.direccionRepository = direccionRepository;
    }
	
	 /* SERVICIOS - DIRECCIÓN */
	
	// GET
	@Override
	public List<Direccion> getTotalidadDirecciones() {
	
	    return this.direccionRepository.getTotalidadDirecciones();
	}
	
	@Override
	public Direccion getDireccion(Integer direccion_id) {

	    return this.direccionRepository.getDireccion(direccion_id);
	}
	
	@Override
	public List<Direccion> getDireccionesPorCliente(Integer cliente_id) {
	
	    return this.direccionRepository.getDireccionesPorCliente(cliente_id);
	}
	
	// POST
	@Override
	public Integer createDireccion(Direccion d) {
	
	    return this.direccionRepository.createDireccion(d);
	}
	
	// PUT
	@Override
	public void updateDireccion(Direccion d) {
	
	    this.direccionRepository.updateDireccion(d);
	}
	
	// DELETE
	@Override
	public void deleteDireccionFisica(Integer direccion_id) {
	
	    this.direccionRepository.deleteDireccionFisica(direccion_id);
	}
    
}
