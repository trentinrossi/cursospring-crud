package com.rossi.cursomc.service;

import java.util.Optional;

import com.rossi.cursomc.model.Cliente;
import com.rossi.cursomc.repository.ClienteRepository;
import com.rossi.cursomc.service.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente buscar(Integer id) {
        Optional<Cliente> cli = repository.findById(id);

        /*
        // Caso for o Spring 1.x
        if (cat == null) {
            // Isso vai fazer com que a classe ResourceExceptionHandler seja executada, retornando o erro 404 para o cliente                        
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName());                        
        }
        */
        
        // Caso seja o Spring 2.x        
        // Isso vai fazer com que a classe ResourceExceptionHandler seja executada, retornando o erro 404 para o cliente                        
        return cli.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }
}
