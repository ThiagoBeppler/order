package com.example.order.models;

import com.example.order.entities.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PedidoDTO {
    private List<Produto> produtos = new ArrayList<>();

    private String comprador;

    private String status;

}
