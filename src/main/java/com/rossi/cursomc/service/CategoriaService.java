package com.rossi.cursomc.service;

import java.util.Optional;

import com.rossi.cursomc.model.Categoria;
import com.rossi.cursomc.repository.CategoriaRepository;
import com.rossi.cursomc.service.exceptions.DataIntegrityException;
import com.rossi.cursomc.service.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria find(Integer id) {
        Optional<Categoria> cat = repository.findById(id);

        /*
        // Caso for o Spring 1.x
        if (cat == null) {
            // Isso vai fazer com que a classe ResourceExceptionHandler seja executada, retornando o erro 404 para o cliente                        
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName());                        
        }
        */
        
        // Caso seja o Spring 2.x        
        // Isso vai fazer com que a classe ResourceExceptionHandler seja executada, retornando o erro 404 para o cliente                        
        return cat.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria inserir(Categoria obj) {
        // Estou setando aqui como nulo para garantir que será inserida uma nova categoria
        // Caso a categoria seja passada com algum id, então este método save vai atualizar ela ao inves de inserir
        obj.setId(null); 
        return repository.save(obj);
    }

    public Categoria update(Categoria obj) {
        find(obj.getId());
        return repository.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
        }
    }
}
