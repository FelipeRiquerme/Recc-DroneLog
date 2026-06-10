package org.example.dronelog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;

public record PilotoRequestDTO(

        @NotBlank
         String nome,

         @NotBlank
         String registroAnac,

         @Email
         String email,

         @FutureOrPresent
         Boolean ativo

) {
}
