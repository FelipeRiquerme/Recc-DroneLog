package org.example.dronelog.service;

import org.example.dronelog.dto.DroneRequestDTO;
import org.example.dronelog.dto.DroneResponseDTO;
import org.example.dronelog.exception.RecursoNaoEncontradoException;
import org.example.dronelog.model.Drone;
import org.example.dronelog.repository.DroneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneService {

    private final DroneRepository droneRepository;

    public DroneService(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    public List<DroneResponseDTO> listar(Boolean disponivel) {
        List<Drone> drones;

        if (disponivel == null) {
            drones = droneRepository.findAll();
        } else {
            drones = droneRepository.findByDisponivel(disponivel);
        }

        return drones.stream().map(this::toResponse).toList();
    }

    public DroneResponseDTO buscarPorId(Long id) {
        return toResponse(buscarDrone(id));
    }

    public DroneResponseDTO cadastrar(DroneRequestDTO dto) {
        Drone drone = new Drone();
        drone.setIdentificador(dto.identificador());
        drone.setModelo(dto.modelo());
        drone.setAutonomiaMinutos(dto.autonomiaMinutos());
        drone.setDisponivel(dto.disponivel() != null ? dto.disponivel() : true);

        return toResponse(droneRepository.save(drone));
    }

    public DroneResponseDTO atualizar(Long id, DroneRequestDTO dto) {
        Drone drone = buscarDrone(id);
            drone.setIdentificador(dto.identificador());
            drone.setModelo(dto.modelo());
            drone.setAutonomiaMinutos(dto.autonomiaMinutos());
        drone.setDisponivel(dto.disponivel() != null ? dto.disponivel() : true);

        drone.setIdentificador(dto.identificador());

        return toResponse(droneRepository.save(drone));
    }

    public void deletar(Long id) {

        Drone drone = buscarDrone(id);
        droneRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Drone não encontrado."));

        droneRepository.delete(drone);
    }

    public Drone buscarDrone(Long id) {
        return droneRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Drone não encontrado."));
    }

    private DroneResponseDTO toResponse(Drone drone) {
        return new DroneResponseDTO(
                drone.getIdDrone(),
                drone.getIdentificador(),
                drone.getModelo(),
                drone.getAutonomiaMinutos(),
                drone.getDisponivel()
        );
    }
}