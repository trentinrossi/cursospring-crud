package com.rossi.cursomc.resources;

import com.rossi.cursomc.model.ItemPedido;
import com.rossi.cursomc.service.ItemPedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/itempedido")
public class ItemPedidoResource {

    @Autowired
    private ItemPedidoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        ItemPedido obj = service.buscar(id);
        return ResponseEntity.ok().body(obj);
    }
}