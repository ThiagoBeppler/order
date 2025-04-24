package com.example.order.repository;

import com.example.order.entities.Pedido;
import com.example.order.service.PedidoService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Pedido save(Pedido pedido);
    List<Pedido> findByStatus(String status);
    Optional<Pedido> findById(Long id);
    Optional<Pedido> findTopByCompradorAndStatusOrderByDataHoraDesc(String comprador, String status);

}