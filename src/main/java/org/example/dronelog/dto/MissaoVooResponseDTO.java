package org.example.dronelog.dto;

import org.example.dronelog.model.StatusMissao;

import java.time.LocalDate;

public record MissaoVooResponseDTO(
        Long idMissao,
        String titulo,
        String localOperacao,
        LocalDate dataPrevista,
        Double areaMapeadaKm2,
        StatusMissao status,
        Long pilotoId,
        String pilotoNome,
        Long droneId,
        String droneIdentificador
) {
}
