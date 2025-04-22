package com.example.order.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
public class Produto {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private Double preco;
}
