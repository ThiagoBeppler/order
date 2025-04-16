package com.example.order.controller;

import com.example.order.entities.Pedido;
import com.example.order.models.PedidoDTO;
import com.example.order.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;

public class PedidoController {
    @Autowired
    private PedidoService service;

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = service.criarPedido(pedidoDTO);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/processados")
    public List<Pedido> getProcessedOrders() {
        return service.listarPedidosProcessados();
    }

    @GetMapping("/id/{id}")
    public Optional<Pedido> getProcessedOrders(@RequestHeader("id") Long id) {
        return service.buscarPedidoPorId(id);
    }
}
