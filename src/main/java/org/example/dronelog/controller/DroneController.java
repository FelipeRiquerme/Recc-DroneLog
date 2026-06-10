package org.example.dronelog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.dronelog.dto.DroneRequestDTO;
import org.example.dronelog.dto.DroneResponseDTO;
import org.example.dronelog.service.DroneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Drones", description = "Rotas para gerenciamento de drones")
@RestController
@RequestMapping("/drones")
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @Operation(summary = "Lista todos os drones")
    @GetMapping
    public List<DroneResponseDTO> listar(@RequestParam(required = false) Boolean disponivel) {
        return droneService.listar(disponivel);
    }

    @GetMapping("/{id}")
    public DroneResponseDTO buscarPorId(@PathVariable Long id) {
        return droneService.buscarPorId(id);
    }

    @PostMapping
    public DroneResponseDTO cadastrar(@RequestBody @Valid DroneRequestDTO dto) {
        return droneService.cadastrar(dto);
    }

    @PutMapping("/{id}")
    public DroneResponseDTO atualizar(@PathVariable Long id, @RequestBody @Valid DroneRequestDTO dto) { 
        return droneService.atualizar(id, dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        droneService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
