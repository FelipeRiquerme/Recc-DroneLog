package org.example.dronelog.repository;

import org.example.dronelog.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, Long> {


    List<Drone> findByDisponivel(Boolean disponivel);
}
