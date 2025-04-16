package com.example.order.models;

import com.example.order.entities.Produto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PedidoDTO {
    private List<Produto> produtos = new ArrayList<>();

    private Double total;

    private String status;

}
