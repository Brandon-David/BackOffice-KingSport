package com.example.demo.departamento.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.departamento.model.Categoria;
import com.example.demo.departamento.model.Departamento;
import com.example.demo.departamento.model.Subcategoria;

public class RSEDepartamentoCatSub implements RowMapper<List<Departamento>>{

	private final RMDepartamento rmDepartamento = new RMDepartamento();
	private final RMCategoria rmCategoria = new RMCategoria();
	private final RMSubcategoria rmSubcategoria = new RMSubcategoria();
	
	public List<Departamento> mapRow(ResultSet rs, int rowNum) throws SQLException {

		Map<Integer, Departamento> departamentoMap = new LinkedHashMap<>();
		
		Map<Integer, Categoria> categoriaMap = new LinkedHashMap<>();
		
		while(rs.next()) {
			
			int departamento_id = rs.getInt("departamento_id");
			
			Departamento departamento = departamentoMap.get(departamento_id);
			if(departamento == null) {
				
				departamento = rmDepartamento.mapDepartamento(rs, 0, "d_");
				departamento.setCategorias(new ArrayList<>());
				
				departamentoMap.put(departamento_id, departamento);
			}
			
			
			int categoria_id = rs.getInt("categoria_id");
			Categoria categoria = categoriaMap.get(categoria_id);
			if(categoria == null) {
				
				categoria = rmCategoria.mapCategoria(rs, 0, "c_");
				categoria.setSubcategorias(new ArrayList<>());
				categoriaMap.put(categoria_id, categoria);
				
				departamento.getCategorias().add(categoria);
			}
			
			Integer subcategoria_id = rs.getInt("subcategoria_id");
			
			if(!rs.wasNull()) {
				
				Subcategoria subcategoria = rmSubcategoria.mapSubcategoria(rs, 0, "sc_");
				categoria.getSubcategorias().add(subcategoria);
			}
			
		}
		
		return new ArrayList<>(departamentoMap.values());
    }
	
	
}
