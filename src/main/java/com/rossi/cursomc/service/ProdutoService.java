package com.rossi.cursomc.service;

import java.util.Optional;

import com.rossi.cursomc.model.Produto;
import com.rossi.cursomc.repository.ProdutoRepository;
import com.rossi.cursomc.service.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto buscar(Integer id) {
        Optional<Produto> cli = repository.findById(id);                       
        return cli.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }
}
