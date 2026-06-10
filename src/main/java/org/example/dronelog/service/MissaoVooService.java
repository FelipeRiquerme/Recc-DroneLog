package org.example.dronelog.service;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.example.dronelog.dto.MissaoVooRequestDTO;
import org.example.dronelog.dto.MissaoVooResponseDTO;
import org.example.dronelog.dto.PilotoResponseDTO;
import org.example.dronelog.exception.RecursoNaoEncontradoException;
import org.example.dronelog.model.Drone;
import org.example.dronelog.model.MissaoVoo;
import org.example.dronelog.model.Piloto;
import org.example.dronelog.model.StatusMissao;
import org.example.dronelog.repository.MissaoVooRepository;
import org.example.dronelog.repository.PilotoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MissaoVooService {

    //TODO: completar ou alterar.
    private final MissaoVooRepository missaoVooRepository;
    private final PilotoRepository pilotoRepository;


    public MissaoVooService(MissaoVooRepository missaoVooRepository, PilotoRepository pilotoRepository) {
        this.missaoVooRepository = missaoVooRepository;
        this.pilotoRepository = pilotoRepository;

    }

    public List<MissaoVooResponseDTO> listar(StatusMissao status, String localOperacao, LocalDate dataPrevista) {
        List<MissaoVoo> missaoVoo;

         missaoVoo = missaoVooRepository.findByStatusAndDescricaoAndData(status, localOperacao, dataPrevista );
        return missaoVoo.stream().map(this::toResponse).toList();

    }

    public MissaoVooResponseDTO buscarPorId(Long id) {

        MissaoVoo missaoVoo = buscarMissao(id);
        return toResponse(missaoVoo);
    }

    public MissaoVooResponseDTO cadastrar(MissaoVooRequestDTO dto) {

        MissaoVoo missaoVoo = new MissaoVoo();
        missaoVoo.setTitulo(dto.titulo());
        missaoVoo.setLocalOperacao(dto.localOperacao());
        missaoVoo.setDataPrevista(dto.dataPrevista());
        missaoVoo.setAreaMapeadaKm2(dto.areaMapeadaKm2());
        missaoVoo.setStatus(dto.status());


        return toResponse(missaoVooRepository.save(missaoVoo));
    }

    public MissaoVooResponseDTO atualizar(Long id, MissaoVooRequestDTO dto) {
        MissaoVoo missaoVoo = buscarMissao(id);
        missaoVoo.setTitulo(dto.titulo());
        missaoVoo.setLocalOperacao(dto.localOperacao());
        missaoVoo.setDataPrevista(dto.dataPrevista());
        missaoVoo.setStatus(dto.status());

        return null;
    }

    public void deletar(Long id) {

        MissaoVoo missaoVoo = buscarMissao(id);
        missaoVooRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Drone não encontrado."));

        missaoVooRepository.delete(missaoVoo);
    }

    private MissaoVoo buscarMissao(Long id) {
        return missaoVooRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Missão de voo não encontrada."));
    }

    private MissaoVooResponseDTO toResponse(MissaoVoo missao) {
        return new MissaoVooResponseDTO(
                missao.getIdMissao(),
                missao.getTitulo(),
                missao.getLocalOperacao(),
                missao.getDataPrevista(),
                missao.getAreaMapeadaKm2(),
                missao.getStatus(),
                missao.getPiloto().getIdPiloto(),
                missao.getPiloto().getNome(),
                missao.getDrone().getIdDrone(),
                missao.getDrone().getIdentificador()

        );



    }
}
