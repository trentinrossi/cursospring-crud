package com.rossi.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.rossi.cursomc.model.Categoria;
import com.rossi.cursomc.model.dto.CategoriaDTO;
import com.rossi.cursomc.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    private CategoriaService service;

    // Retorna uma CategoriaDTO onde contem somente o id e nome
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<Categoria> list = service.findAll();

        // Transformo as categorias recuperadas do BD em categorias DTO        
        List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
        
        return ResponseEntity.ok().body(listDto);
    }

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
    
    @DeleteMapping(value = "/{id}")
    // Outra forma de fazer a anotação = @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Categoria> delete(@PathVariable Integer id) {
        // Aqui dentro do Service existe um tratamento de erro, caso este não ocorra, será executada a linha abaixo 'return', senão o return abaixo não vai ocorrer
        service.delete(id); 

        return ResponseEntity.noContent().build();
    }

    // https://www.udemy.com/course/spring-boot-ionic/learn/lecture/8186076#overview
    // https://github.com/trentinrossi/springboot2-ionic-backend/commit/40bd7603cf5405a81e142cf968be427c7e6a3b3c
    @GetMapping(value = "/page")
    public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {

        // Retorna as categorias
        Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
        
        // Converte para DTO
        Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));  
        
		return ResponseEntity.ok().body(listDto);
	}
    
}