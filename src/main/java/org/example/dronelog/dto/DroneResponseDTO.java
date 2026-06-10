package org.example.dronelog.dto;

public record DroneResponseDTO(
        Long idDrone,
        String identificador,
        String modelo,
        Integer autonomiaMinutos,
        Boolean disponivel
) {
}
