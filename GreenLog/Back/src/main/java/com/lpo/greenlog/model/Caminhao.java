package com.lpo.greenlog.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Caminhao {

    @Id
    private String placa;

    // Associa Caminhao a Motorista (FK)
    @ManyToOne
    @JoinColumn(name = "cpf_motorista")  // FK para motorista(cpf)
    private Motorista motorista;

    // Enum para tipo de resíduo
    @Enumerated(EnumType.STRING)
    private TipoResiduo tipoResiduo;
}
