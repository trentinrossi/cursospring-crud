package com.rossi.cursomc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preco;

    // Produto tem várias categorias 
    @JsonIgnore // Do outro lado já foi buscado os objetos, então aqui ele vai omitir a lista de categorias para cada produto 
    @ManyToMany
    @JoinTable(name = "PRODUTO_CATEGORIA", // Nome da tabela intermediária
        joinColumns = @JoinColumn(name = "produto_id"), 
        inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias = new ArrayList<>();

    // Produto tem que conhecer os itens pedido associados a ela
    @JsonIgnore
    @OneToMany(mappedBy = "id.produto")    
    private Set<ItemPedido> itens = new HashSet<>();

    // Verificar os pedidos atrepados a este produto
    @JsonIgnore // Tudo que é começado com get é serializado, então se coloca isso para não serializar
    public List<Pedido> getPedidos() {
        List<Pedido> lista = new ArrayList<>();
        for (ItemPedido x : itens) {
            lista.add(x.getPedido());
        }
        return lista;
    }

}