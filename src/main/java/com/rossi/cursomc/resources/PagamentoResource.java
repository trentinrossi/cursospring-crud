package com.rossi.cursomc.resources;

import com.rossi.cursomc.model.Pagamento;
import com.rossi.cursomc.service.PagamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pagamentos")
public class PagamentoResource {

    @Autowired
    private PagamentoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Pagamento obj = service.buscar(id);
        return ResponseEntity.ok().body(obj);
    }
}