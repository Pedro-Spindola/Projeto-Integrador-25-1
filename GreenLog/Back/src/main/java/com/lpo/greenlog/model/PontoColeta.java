package com.lpo.greenlog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "pontos_coleta")
public class PontoColeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bairro_id", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Bairro bairro;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "responsavel", length = 255)
    private String responsavel;

    @Column(name = "telefone_responsavel", length = 50)
    private String telefoneResponsavel;

    @Column(name = "email_responsavel", length = 255)
    private String emailResponsavel;

    @Column(name = "endereco", length = 255)
    private String endereco;

    @Column(name = "horario_funcionamento", length = 100)
    private String horarioFuncionamento;

    @Column(name = "tipos_residuo_aceitos", length = 255)
    private String tiposResiduoAceitos;
}
