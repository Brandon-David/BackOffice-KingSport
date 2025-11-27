package com.example.demo.pedidos.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.cliente.model.Cliente;
import com.example.demo.cliente.repository.RMCliente;
import com.example.demo.pedidos.model.Pedido;

public class RMPedidoCliente implements RowMapper<Pedido> {

    private final RMPedido rmPedido = new RMPedido();
    private final RMCliente rmCliente = new RMCliente();

	@Override
	public Pedido mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Pedido pedido = rmPedido.mapRow(rs, rowNum);
		Cliente cliente = rmCliente.mapRow(rs, rowNum);
		
		pedido.setCliente(cliente);
		
		return pedido;
	}
	
}
