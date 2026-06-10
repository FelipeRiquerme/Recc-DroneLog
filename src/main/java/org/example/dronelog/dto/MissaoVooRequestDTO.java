package org.example.dronelog.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.dronelog.model.StatusMissao;

import java.time.LocalDate;

public record MissaoVooRequestDTO(
        @NotBlank
        String titulo,

        @NotBlank
        String localOperacao,

        @FutureOrPresent
        LocalDate dataPrevista,

        @Positive
        @NotNull
        Double areaMapeadaKm2,

        @NotBlank
        StatusMissao status,

        @NotBlank
        String pilotoNome,

        @NotBlank
        String droneIdentificador




) {
}
