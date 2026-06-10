package org.example.dronelog.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class MissaoVoo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMissao;

    private String titulo;



    private String localOperacao;

    private LocalDate dataPrevista;

    private Double areaMapeadaKm2;

    @Enumerated(EnumType.STRING)
    private StatusMissao status;


    @ManyToOne
    @JoinColumn(name = "Piloto")
    private Piloto piloto;


    @ManyToOne
    @JoinColumn(name = "Drone")
    private Drone drone;


    public MissaoVoo() {

    }
}
