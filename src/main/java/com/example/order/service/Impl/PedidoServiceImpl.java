package com.example.order.service.Impl;

import com.example.order.entities.Pedido;
import com.example.order.models.PedidoDTO;
import com.example.order.repository.PedidoRepository;
import com.example.order.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Override
    public Pedido criarPedido(PedidoDTO pedidoDTO) {

        Pedido pedido = new Pedido(
            null,
            LocalDateTime.now(),
            pedidoDTO.getProdutos(),
            pedidoDTO.getTotal(),
            "PROCESSADO"
        );

       return pedidoRepository.save(pedido);
    }

    @Override
    public List<Pedido> listarPedidosProcessados() {
        return pedidoRepository.findByStatus("PROCESSADO");
    }

    @Override
    public Optional<Pedido> buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id);
    }
}
