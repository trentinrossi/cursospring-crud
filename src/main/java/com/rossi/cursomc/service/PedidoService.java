package com.rossi.cursomc.service;

import java.util.Optional;

import com.rossi.cursomc.model.Pedido;
import com.rossi.cursomc.repository.PedidoRepository;
import com.rossi.cursomc.service.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public Pedido buscar(Integer id) {
        Optional<Pedido> cli = repository.findById(id);                       
        return cli.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }
}
