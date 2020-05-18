package com.rossi.cursomc.service;

import java.util.Optional;

import com.rossi.cursomc.model.ItemPedido;
import com.rossi.cursomc.repository.ItemPedidoRepository;
import com.rossi.cursomc.service.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository repository;

    public ItemPedido buscar(Integer id) {
        Optional<ItemPedido> cli = repository.findById(id);                       
        return cli.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + ItemPedido.class.getName()));
    }
}
