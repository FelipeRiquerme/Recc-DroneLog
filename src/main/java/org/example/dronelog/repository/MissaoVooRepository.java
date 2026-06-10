package org.example.dronelog.repository;

import org.example.dronelog.model.MissaoVoo;
import org.example.dronelog.model.Piloto;
import org.example.dronelog.model.StatusMissao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MissaoVooRepository extends JpaRepository<MissaoVoo, Long> {

    List<MissaoVoo> findByStatusAndDescricaoAndData(StatusMissao status, String localOperacao, LocalDate dataPrevista);
}
