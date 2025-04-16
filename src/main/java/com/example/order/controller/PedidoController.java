package com.example.order.controller;

import com.example.order.entities.Pedido;
import com.example.order.models.PedidoDTO;
import com.example.order.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoService.criarPedido(pedidoDTO);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/processados")
    public List<Pedido> getProcessedOrders() {
        return pedidoService.listarPedidosProcessados();
    }

    @GetMapping("/id/{id}")
    public Optional<Pedido> getProcessedOrders(@PathVariable("id") Long id) {
        return pedidoService.buscarPedidoPorId(id);
    }

    @GetMapping("/teste")
    public String teste() {
        return "teste";
    }
}
