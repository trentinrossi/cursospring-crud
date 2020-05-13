package com.rossi.cursomc.model;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
}