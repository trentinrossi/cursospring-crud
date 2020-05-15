package com.rossi.cursomc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;

    public Endereco(Integer id, String logradouro, String numero, String complemento, String bairro, String cep,
            Cliente cliente, Cidade cidade) {
        super();
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cliente = cliente;
        this.setCidade(cidade);
    }

    @JsonBackReference // Endereço não pode trazer os clientes atrelados a ele
    @ManyToOne
    @JoinColumn(name = "cliente_id") // Nome do campo a ser criado no banco
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;
}