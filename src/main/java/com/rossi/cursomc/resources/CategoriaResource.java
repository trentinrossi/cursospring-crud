package com.rossi.cursomc.resources;

import java.net.URI;

import com.rossi.cursomc.model.Categoria;
import com.rossi.cursomc.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    @GetMapping(value = "/{id}")
    // Outra forma de fazer a anotação = @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id) {
        // Aqui dentro do Service existe um tratamento de erro, caso este não ocorra, será executada a linha abaixo 'return', senão o return abaixo não vai ocorrer
        Categoria obj = service.find(id); 

        return ResponseEntity.ok().body(obj);
    }

    // RequestBody faz com que o objeto seja convertido para JSON automaticamente
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
        obj = service.inserir(obj);

        // Faz com que seja retornado no Location da resposta a URL para que se possa navegar até a nova categoria criada
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        
        // Created retorna o status 201 colocando o corpo da resposta vazio
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Categoria obj) {
        obj.setId(id);
        obj = service.update(obj);
        
        return ResponseEntity.noContent().build();
    }    
}