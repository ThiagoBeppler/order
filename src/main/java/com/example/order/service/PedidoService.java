package com.example.order.service;

import com.example.order.entities.Pedido;
import com.example.order.models.PedidoDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PedidoService {
    Pedido criarPedido(PedidoDTO pedidoDTO);

    List<Pedido> listarPedidosProcessados();

    Optional<Pedido> buscarPedidoPorId(Long id);
}
