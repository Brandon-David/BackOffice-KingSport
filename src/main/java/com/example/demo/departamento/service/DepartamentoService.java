package com.example.demo.departamento.service;

import java.util.List;

import com.example.demo.departamento.model.Categoria;
import com.example.demo.departamento.model.Departamento;
import com.example.demo.departamento.model.Subcategoria;

public interface DepartamentoService {

	/* SERVICIOS DE DEPARTAMENTO */
	
		// GET
	List<Departamento> getTotalidadDepartamentos();
	Departamento getDepartamento(Integer departamento_id);
	
		// POST
	Integer createDepartamento(Departamento d);
	
		// PUT
	void updateDepartamento(Departamento d);
	
		// DELETE
	void deleteDepartamentoFisico(Integer departamento_id);
	
	/* SERVICIOS DE CATEGORIA */
	
		// GET
	List<Categoria> getTotalidadCategorias();
	Categoria getCategoria(Integer categoria_id);
	
		// POST
	Integer createCategoria(Categoria c);
	
		// PUT
	void updateCategoria(Categoria c);
	
		// DELETE
	void deleteCategoriaFisico(Integer categoria_id);
	
	/* SERVICIOS DE SUBCATEGORIA */
	
		// GET
	List<Subcategoria> getTotalidadSubcategorias();
	Subcategoria getSubcategoria(Integer subcategoria_id);
	
		// POST
	Integer createSubcategoria(Subcategoria s);
	
		// PUT
	void updateSubcategoria(Subcategoria s);
	
		// DELETE
	void deleteSubcategoriaFisico(Integer subcategoria_id);

}
