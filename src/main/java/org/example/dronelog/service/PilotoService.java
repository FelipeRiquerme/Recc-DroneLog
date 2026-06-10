package org.example.dronelog.service;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import org.example.dronelog.dto.DroneResponseDTO;
import org.example.dronelog.dto.PilotoRequestDTO;
import org.example.dronelog.dto.PilotoResponseDTO;
import org.example.dronelog.exception.RecursoNaoEncontradoException;
import org.example.dronelog.model.Drone;
import org.example.dronelog.model.Piloto;
import org.example.dronelog.repository.DroneRepository;
import org.example.dronelog.repository.MissaoVooRepository;
import org.example.dronelog.repository.PilotoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PilotoService {


    PilotoRepository pilotoRepository;

    public PilotoService(PilotoRepository pilotoRepository) {
        this.pilotoRepository = pilotoRepository;
    }

    public List<PilotoResponseDTO> listar(String nome) {

        return pilotoRepository.findByNome(nome).stream().map(this::toResponse).toList();


    }

    public PilotoResponseDTO buscarPorId(Long id) {
        Piloto piloto = buscarPiloto(id);
        return toResponse(piloto);
    }

    public PilotoResponseDTO cadastrar(PilotoRequestDTO dto) {
        Piloto piloto = new Piloto();
        piloto.setNome(dto.nome());
        piloto.setRegistroAnac(dto.registroAnac());
        piloto.setEmail(dto.email());
        piloto.setAtivo(dto.ativo());



        return toResponse(pilotoRepository.save(piloto));
    }

    public PilotoResponseDTO atualizar(Long id, PilotoRequestDTO dto) {
        Piloto piloto = buscarPiloto(id);
            piloto.setNome(dto.nome());
            piloto.setRegistroAnac(dto.registroAnac());
            piloto.setEmail(dto.email());
            piloto.setAtivo(dto.ativo());


        return toResponse(pilotoRepository.save(piloto));
    }

    public void deletar(Long id) {
        Piloto piloto = buscarPiloto(id);

        // TODO: decidir como tratar pilotos com missões vinculadas.
        pilotoRepository.delete(piloto);
    }

    public Piloto buscarPiloto(Long id) {
        return pilotoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Piloto não encontrado."));
    }

    private PilotoResponseDTO toResponse(Piloto piloto) {

        return new PilotoResponseDTO(
                piloto.getIdPiloto(),
                piloto.getNome(),
                piloto.getRegistroAnac(),
                piloto.getEmail(),
                piloto.getAtivo()

        );
    }
}
