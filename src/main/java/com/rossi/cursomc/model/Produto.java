package com.rossi.cursomc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preco;

    // Produto tem várias categorias 
    @JsonBackReference // Do outro lado já foi buscado os objetos, então aqui ele vai omitir a lista de categorias para cada produto 
    @ManyToMany
    @JoinTable(name = "PRODUTO_CATEGORIA", // Nome da tabela intermediária
        joinColumns = @JoinColumn(name = "produto_id"), 
        inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias = new ArrayList<>();

}