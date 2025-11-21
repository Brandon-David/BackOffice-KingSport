package com.example.demo.direccion.repository;


import java.util.List;

import com.example.demo.direccion.model.Direccion;

public interface DireccionRepository {
 
	 // GET
    List<Direccion> getTotalidadDirecciones();
    Direccion getDireccion(Integer direccion_id);
    List<Direccion> getDireccionesPorCliente(Integer cliente_id);

    // POST
    Integer createDireccion(Direccion d);

    // PUT
    void updateDireccion(Direccion d);

    // DELETE
    void deleteDireccionFisica(Integer direccion_id);
    
}
