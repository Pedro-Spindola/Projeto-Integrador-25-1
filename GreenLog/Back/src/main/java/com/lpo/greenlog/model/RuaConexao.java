package com.lpo.greenlog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ruas_conexoes")
public class RuaConexao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Ignorar atributos internos do proxy de Bairro ao serializar
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bairro_origem_id", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Bairro bairroOrigem;

    // Ignorar atributos internos do proxy de Bairro ao serializar
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bairro_destino_id", nullable = false)
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Bairro bairroDestino;

    @Column(name = "distancia_km", nullable = false)
    private Double distanciaKm;
}
