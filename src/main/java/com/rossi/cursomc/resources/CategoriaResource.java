package com.rossi.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import com.rossi.cursomc.model.Categoria;
import com.rossi.cursomc.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @GetMapping(value = "/{id}")
    // Outra forma de fazer a anotação = @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        // Aqui dentro do Service existe um tratamento de erro, caso este não ocorra, será executada a linha abaixo 'return', senão o return abaixo não vai ocorrer
        Categoria obj = service.buscar(id); 

        return ResponseEntity.ok().body(obj);
    }
}