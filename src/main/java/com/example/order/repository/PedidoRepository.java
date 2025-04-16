package com.example.order.repository;

import com.example.order.entities.Pedido;
import com.example.order.service.PedidoService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Pedido save(Pedido pedido);
    List<Pedido> findByStatus(String status);
    List<Pedido> findById(String id);
}