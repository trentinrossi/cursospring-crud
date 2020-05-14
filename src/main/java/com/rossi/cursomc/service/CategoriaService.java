package com.rossi.cursomc.service;

import java.util.Optional;

import com.rossi.cursomc.model.Categoria;
import com.rossi.cursomc.repository.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria buscar(Integer id) {
        Optional<Categoria> cat = repository.findById(id);
        return cat.orElse(null);
    }
}
