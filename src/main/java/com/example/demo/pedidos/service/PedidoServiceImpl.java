package com.example.demo.pedidos.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.cliente.repository.ClienteRepository;
import com.example.demo.pedidos.model.Envio;
import com.example.demo.pedidos.model.Pedido;
import com.example.demo.pedidos.repository.PedidoRepository;
import com.example.demo.usuario.service.UsuarioServiceImpl;

@Service
public class PedidoServiceImpl implements PedidoService{

	private static final Logger log = LoggerFactory.getLogger(PedidoServiceImpl.class);

    private PedidoRepository pedidoRepository;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository) {

        this.pedidoRepository = pedidoRepository;
    }
	
    /* SERVICIOS DE PEDIDO */

    // GET
    @Override
    public List<Pedido> getTotalidadPedidos() {

        return this.pedidoRepository.getTotalidadPedidos();
    }

    @Override
    public List<Pedido> getTotalidadPedidosCliente() {

        return this.pedidoRepository.getTotalidadPedidosCliente();
    }

    @Override
    public Pedido getPedidoPorId(Integer pedido_id) {

        return this.pedidoRepository.getPedidoPorId(pedido_id);
    }

    @Override
    public List<Pedido> getPedidosPorCliente(Integer cliente_id) {

        return this.pedidoRepository.getPedidosPorCliente(cliente_id);
    }

    // POST
    @Override
    public Integer createPedido(Pedido p) {

        return this.pedidoRepository.createPedido(p);
    }

    // PUT
    @Override
    public void updatePedido(Pedido p) {

        this.pedidoRepository.updatePedido(p);
    }

    // DELETE
    @Override
    public void deletePedidoFisico(Integer pedido_id) {

        this.pedidoRepository.deletePedidoFisico(pedido_id);
    }


    /* SERVICIOS DE ENVIO */

    // GET
    @Override
    public List<Envio> getTotalidadEnvios() {

        return this.pedidoRepository.getTotalidadEnvios();
    }

    @Override
    public Envio getEnvioPorId(Integer envio_id) {

        return this.pedidoRepository.getEnvioPorId(envio_id);
    }

    @Override
    public List<Envio> getEnviosPorPedido(Integer pedido_id) {

        return this.pedidoRepository.getEnviosPorPedido(pedido_id);
    }

    // POST
    @Override
    public Integer createEnvio(Envio e) {

        return this.pedidoRepository.createEnvio(e);
    }

}
