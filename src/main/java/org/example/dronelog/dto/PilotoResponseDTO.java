package org.example.dronelog.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public record PilotoResponseDTO(

        Long idPiloto,
        String nome,
        String registroAnac,
        String email,
        Boolean ativo
        ) {
}
