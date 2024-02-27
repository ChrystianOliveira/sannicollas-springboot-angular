package br.com.sannicollas.controller;

import br.com.sannicollas.dto.TurmaDTO;
import br.com.sannicollas.dto.TurmaFiltroDTO;
import br.com.sannicollas.mapper.TurmaMapper;
import br.com.sannicollas.model.Turma;
import br.com.sannicollas.service.TurmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/turmas")
@RequiredArgsConstructor
@Tag(name = "API REST Turma")
public class TurmaController {

    private final TurmaService turmaService;

    @Operation(summary = "Busca uma paginação de TURMAS", description = "possibilidade de filtrar pelo TURNO, OBS: São retornadas apenas as turmas " +
            "que possuem pelo menos um aluno cadastrado")
    @GetMapping
    public Page<TurmaDTO> buscarTurmas(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size,
            @RequestParam(value = "turnoId", required = false) final Long turnoId) {
        TurmaFiltroDTO filtro = TurmaFiltroDTO.builder()
                .turno(turnoId)
                .build();
        Page<Turma> turmas = turmaService.buscarTurmas(page, size, filtro);

        return turmas.map(TurmaMapper.INST::modelToDto);
    }

}
