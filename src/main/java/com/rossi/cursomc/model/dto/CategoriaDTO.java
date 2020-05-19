package com.rossi.cursomc.model.dto;

import java.io.Serializable;

import com.rossi.cursomc.model.Categoria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;

    // Esse contrutor que transforma o objeto Categoria em um CategoriaDTO
    // https://www.udemy.com/course/spring-boot-ionic/learn/lecture/8169646#overview
    public CategoriaDTO(Categoria obj) {
        id = obj.getId();
        nome = obj.getNome();
    }

}