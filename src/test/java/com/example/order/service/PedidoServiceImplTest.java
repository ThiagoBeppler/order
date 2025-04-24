package com.example.order.service;

import com.example.order.entities.Pedido;
import com.example.order.entities.Produto;
import com.example.order.models.PedidoDTO;
import com.example.order.repository.PedidoRepository;
import com.example.order.service.Impl.PedidoServiceImpl;
import com.example.order.service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarPedido_DeveCriarPedidoComSucesso() {

        ArrayList produtos = new ArrayList();

        Produto produto1 = new Produto(null, "Produto1", 10.0);

        produtos.add(produto1);

        PedidoDTO pedidoDTO = new PedidoDTO(produtos,"comprador", "Aberto");
        Pedido pedidoSalvo = new Pedido(1L, "comprador1", LocalDateTime.now(), pedidoDTO.getProdutos(), 10.0, "PROCESSADO");

        when(pedidoRepository.save(any(Pedido.class))).thenReturn(pedidoSalvo);

        Pedido resultado = pedidoService.criarPedido(pedidoDTO);

        assertNotNull(resultado);
        assertEquals("comprador1", resultado.getComprador());
        assertEquals(10.0, resultado.getTotal());
        assertEquals("PROCESSADO", resultado.getStatus());
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }

    @Test
    void listarPedidosProcessados_DeveRetornarListaDePedidos() {
        List<Pedido> pedidos = Arrays.asList(new Pedido(1L, "comprador1", LocalDateTime.now(), null, 10.0, "PROCESSADO"));
        when(pedidoRepository.findByStatus("PROCESSADO")).thenReturn(pedidos);

        List<Pedido> resultado = pedidoService.listarPedidosProcessados();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("PROCESSADO", resultado.get(0).getStatus());
        verify(pedidoRepository, times(1)).findByStatus("PROCESSADO");
    }

    @Test
    void buscarPedidoPorId_DeveRetornarPedidoQuandoEncontrado() {
        Pedido pedido = new Pedido(1L, "comprador1", LocalDateTime.now(), null, 10.0, "PROCESSADO");
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        Optional<Pedido> resultado = pedidoService.buscarPedidoPorId(1L);

        assertTrue(resultado.isPresent());
        assertEquals(1L, resultado.get().getId());
        verify(pedidoRepository, times(1)).findById(1L);
    }

    @Test
    void buscarPedidoPorId_DeveRetornarVazioQuandoNaoEncontrado() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Pedido> resultado = pedidoService.buscarPedidoPorId(1L);

        assertFalse(resultado.isPresent());
        verify(pedidoRepository, times(1)).findById(1L);
    }

    @Test
    void validarDuplicidadeSequencial_DeveLancarExcecaoQuandoDuplicado() {
        Pedido ultimoPedido = new Pedido(1L, "comprador1", LocalDateTime.now(), null, 10.0, "PROCESSADO");
        when(pedidoRepository.findTopByCompradorAndStatusOrderByDataHoraDesc("comprador1", "PROCESSADO"))
                .thenReturn(Optional.of(ultimoPedido));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                pedidoService.validarDuplicidadeSequencial(10.0, "comprador1"));

        assertEquals("Pedido recusado, você não pode fazer dois pedidos de mesmo valor sequencialmente, pedidoId: null", exception.getMessage());
        verify(pedidoRepository, times(1)).save(any(Pedido.class));
    }

    @Test
    void validarDuplicidadeSequencial_NaoDeveLancarExcecaoQuandoNaoDuplicado() {
        when(pedidoRepository.findTopByCompradorAndStatusOrderByDataHoraDesc("comprador1", "PROCESSADO"))
                .thenReturn(Optional.empty());

        assertDoesNotThrow(() -> pedidoService.validarDuplicidadeSequencial(10.0, "comprador1"));
        verify(pedidoRepository, never()).save(any(Pedido.class));
    }
}