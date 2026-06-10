package org.example.dronelog.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class Piloto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPiloto;

    private String nome;

    private String registroAnac;

    private String email;

    private Boolean ativo;

    @OneToMany(mappedBy = "piloto")
    private List<MissaoVoo> missoes = new ArrayList<>();

    public Piloto() {
    }
}
