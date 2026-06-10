package org.example.dronelog.repository;

import org.example.dronelog.model.Drone;
import org.example.dronelog.model.Piloto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PilotoRepository extends JpaRepository<Piloto, Long> {

    List<Piloto> findByNome(String nome);
}
