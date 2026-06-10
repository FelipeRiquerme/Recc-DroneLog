package org.example.dronelog.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.example.dronelog.dto.DroneRequestDTO;
import org.example.dronelog.dto.DroneResponseDTO;
import org.example.dronelog.dto.PilotoRequestDTO;
import org.example.dronelog.dto.PilotoResponseDTO;
import org.example.dronelog.model.Piloto;
import org.example.dronelog.service.PilotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pilotos")
public class PilotoController {

    private final PilotoService pilotoService;

    public PilotoController(PilotoService pilotoService) {
        this.pilotoService = pilotoService;
    }

    @Operation(summary = "Lista todos os pilotos")
    @GetMapping
    public List<PilotoResponseDTO> listar(@RequestParam(required = false) String nome) {

        return pilotoService.listar(nome);
    }
    @GetMapping("/{id}")
    public PilotoResponseDTO buscarPorId(@PathVariable  Long id) {
        return pilotoService.buscarPorId(id);
    }

    @PostMapping
    public PilotoResponseDTO cadastrar(@RequestBody @Valid PilotoRequestDTO dto) {
        return pilotoService.cadastrar(dto);
    }

    @PutMapping("/{id}")
    public PilotoResponseDTO atualizar(@PathVariable Long id, @RequestBody @Valid PilotoRequestDTO dto) {

        return pilotoService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        pilotoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
