package com.example.demo.diseño.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.diseño.model.Diseño;
import com.example.demo.diseño.repository.DiseñoRepository;

@Service
public class DiseñoServiceImpl implements DiseñoService{

    private static final Logger log = LoggerFactory.getLogger(DiseñoServiceImpl.class);

    private final DiseñoRepository diseñoRepository;

    @Autowired
    DiseñoServiceImpl(DiseñoRepository diseñoRepository) {
    	
        this.diseñoRepository = diseñoRepository;
    }

    /* SERVICIOS - DISEÑO */

    // GET
    @Override
    public List<Diseño> getTotalidadDiseños(String estado) {

        return this.diseñoRepository.getTotalidadDiseños(estado);
    }

    @Override
    public Diseño getDiseño(Integer diseno_id) {

        return this.diseñoRepository.getDiseño(diseno_id);
    }

    @Override
    public List<Diseño> getDiseñosPorCliente(Integer cliente_id) {

        return this.diseñoRepository.getDiseñosPorCliente(cliente_id);
    }

    // POST
    @Override
    public Integer createDiseño(Diseño d) {

        return this.diseñoRepository.createDiseño(d);
    }

    // PUT
    @Override
    public void updateDiseño(Diseño d) {

        log.info("Actualizando diseño ID: {}", d.getDiseno_id());
        this.diseñoRepository.updateDiseño(d);
    }

    @Override
    public void updateEstadoDiseño(Integer diseno_id, String estado) {

        this.diseñoRepository.updateEstadoDiseño(diseno_id, estado);
    }

    // DELETE
    @Override
    public void deleteDiseñoFisico(Integer diseno_id) {

        this.diseñoRepository.deleteDiseñoFisico(diseno_id);
    }
    
}
