package com.rossi.cursomc.service;

import java.util.Optional;

import com.rossi.cursomc.model.Pagamento;
import com.rossi.cursomc.repository.PagamentoRepository;
import com.rossi.cursomc.service.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository repository;

    public Pagamento buscar(Integer id) {
        Optional<Pagamento> cli = repository.findById(id);                       
        return cli.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pagamento.class.getName()));
    }
}
