package com.rossi.cursomc.resources;

import com.rossi.cursomc.model.Cliente;
import com.rossi.cursomc.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @GetMapping(value = "/{id}")
    // Outra forma de fazer a anotação = @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id) {
        // Aqui dentro do Service existe um tratamento de erro, caso este não ocorra, será executada a linha abaixo 'return', senão o return abaixo não vai ocorrer
        Cliente obj = service.find(id); 

        return ResponseEntity.ok().body(obj);
    }
}