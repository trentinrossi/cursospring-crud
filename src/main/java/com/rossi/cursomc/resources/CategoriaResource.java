package com.rossi.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import com.rossi.cursomc.model.Categoria;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @GetMapping
    // Outra forma de fazer
    // @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> listar() {

        Categoria cat1 = new Categoria();
        cat1.setId(1);
        cat1.setNome("Categoria 1");
        Categoria cat2 = new Categoria();
        cat2.setId(2);
        cat2.setNome("Categoria 2");

        List<Categoria> lista = new ArrayList<>();
        lista.add(cat1);
        lista.add(cat2);


        return lista;
    }
}