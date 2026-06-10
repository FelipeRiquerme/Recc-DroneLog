package org.example.dronelog.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDrone;

    private String identificador;

    private String modelo;

    private Integer autonomiaMinutos;

    private Boolean disponivel;


    @OneToMany(mappedBy = "drone")
    private List<MissaoVoo> missoes;

    public Drone() {
    }




}
