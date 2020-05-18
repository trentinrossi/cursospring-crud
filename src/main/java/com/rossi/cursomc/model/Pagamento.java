package com.rossi.cursomc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rossi.cursomc.model.enums.EstadoPagamento;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Aqui eu uso quando tem herança
public abstract class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id; // o ID sempre deve ser o mesmo ID do Pedido, por isso a anotação abaixo sobre o
                        // atributo pedido
    private Integer estado;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId // Aqui eu digo que será o mesmo ID do pedido
    private Pedido pedido;

    public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
        super();
        this.id = id;
        this.estado = estado.getCod();
        this.pedido = pedido;
    }

}