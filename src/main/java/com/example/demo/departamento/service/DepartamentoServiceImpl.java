package com.example.demo.departamento.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.departamento.model.Categoria;
import com.example.demo.departamento.model.Departamento;
import com.example.demo.departamento.model.Subcategoria;
import com.example.demo.departamento.repository.DepartamentoRepository;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    private static final Logger log = LoggerFactory.getLogger(DepartamentoServiceImpl.class);

    private final DepartamentoRepository departamentoRepository;

    @Autowired
    public DepartamentoServiceImpl(DepartamentoRepository departamentoRepository) {
        
    	this.departamentoRepository = departamentoRepository; // Inyección de dependencias
    }

    /* SERVICIOS DE DEPARTAMENTO */

    @Override
    public List<Departamento> getTotalidadDepartamentos() {

        return this.departamentoRepository.getTotalidadDepartamentos();
    }

    @Override
    public Departamento getDepartamento(Integer departamento_id) {

        return this.departamentoRepository.getDepartamento(departamento_id);
    }

    @Override
    public Integer createDepartamento(Departamento d) {

        return this.departamentoRepository.createDepartamento(d);
    }

    @Override
    public void updateDepartamento(Departamento d) {

        this.departamentoRepository.updateDepartamento(d);
    }

    @Override
    public void deleteDepartamentoFisico(Integer departamento_id) {

        this.departamentoRepository.deleteDepartamentoFisico(departamento_id);
    }

    /* SERVICIOS DE CATEGORIA */

    @Override
    public List<Categoria> getTotalidadCategorias() {

        return this.departamentoRepository.getTotalidadCategorias();
    }

    @Override
    public Categoria getCategoria(Integer categoria_id) {

        return this.departamentoRepository.getCategoria(categoria_id);
    }

    @Override
    public Integer createCategoria(Categoria c) {

        return this.departamentoRepository.createCategoria(c);
    }

    @Override
    public void updateCategoria(Categoria c) {

        this.departamentoRepository.updateCategoria(c);
    }

    @Override
    public void deleteCategoriaFisico(Integer categoria_id) {

        this.departamentoRepository.deleteCategoriaFisico(categoria_id);
    }

    /* SERVICIOS DE SUBCATEGORIA */

    @Override
    public List<Subcategoria> getTotalidadSubcategorias() {

        return this.departamentoRepository.getTotalidadSubcategorias();
    }

    @Override
    public Subcategoria getSubcategoria(Integer subcategoria_id) {

        return this.departamentoRepository.getSubcategoria(subcategoria_id);
    }

    @Override
    public Integer createSubcategoria(Subcategoria s) {

        return this.departamentoRepository.createSubcategoria(s);
    }

    @Override
    public void updateSubcategoria(Subcategoria s) {

        this.departamentoRepository.updateSubcategoria(s);
    }

    @Override
    public void deleteSubcategoriaFisico(Integer subcategoria_id) {

        this.departamentoRepository.deleteSubcategoriaFisico(subcategoria_id);
    }
}
