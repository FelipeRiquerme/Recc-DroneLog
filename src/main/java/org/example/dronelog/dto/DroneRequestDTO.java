package org.example.dronelog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DroneRequestDTO(

        String identificador,

        @NotBlank
        String modelo,

        @Positive @NotNull
        Integer autonomiaMinutos,

        @NotBlank
        Boolean disponivel
) {
}
