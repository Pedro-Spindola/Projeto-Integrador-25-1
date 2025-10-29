package com.lpo.greenlog.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Motorista {

    @Id
    @Column(length = 11)
    private String cpf;

    private String nome;

    @Temporal(TemporalType.DATE)
    private Date dataNascimento;
}
