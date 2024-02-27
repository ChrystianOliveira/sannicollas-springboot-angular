package br.com.sannicollas.controller;

import br.com.sannicollas.dto.AlunoCreateDTO;
import br.com.sannicollas.dto.AlunoDTO;
import br.com.sannicollas.dto.AlunoFiltroDTO;
import br.com.sannicollas.dto.AlunosListUUID;
import br.com.sannicollas.mapper.AlunoMapper;
import br.com.sannicollas.model.Aluno;
import br.com.sannicollas.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
@Tag(name = "API REST Aluno")
public class AlunoController {

    private final AlunoService alunoService;

    @Operation(summary = "Busca um ALUNO por ID")
    @GetMapping("/{id}")
    public AlunoDTO buscarAluno(@PathVariable UUID id) {
        return AlunoMapper.INST.modelToDTO(alunoService.buscarPorId(id));
    }

    @Operation(summary = "Busca uma paginação de ALUNOS com a possibilidade de filtrar pelo ID da TURMA e o NOME")
    @GetMapping
    public Page<AlunoDTO> buscarAlunos(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "20") Integer size,
            @RequestParam(value = "nome", required = false) final String nome,
            @RequestParam(value = "turmaId", required = false) final Long turmaId) {
        AlunoFiltroDTO filtro = AlunoFiltroDTO.builder()
                .nome(nome)
                .turma(turmaId)
                .build();
        Page<Aluno> alunos = alunoService.buscarAlunos(page, size, filtro);

       return alunos.map(AlunoMapper.INST::modelToDTO);
    }

    @Operation(summary = "Cadastro de ALUNO")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public AlunoDTO cadastrarAluno(@RequestBody @Valid AlunoCreateDTO formAluno) {
        Aluno aluno = alunoService.cadastrarAluno(AlunoMapper.INST.dtoToModel(formAluno));
        return AlunoMapper.INST.modelToDTO(aluno);
    }

    @Operation(summary = "Atualiza os dados de um ALUNO")
    @PutMapping("/{id}")
    public AlunoDTO atualizarAluno(@Valid @RequestBody AlunoCreateDTO formAluno, @PathVariable UUID id) {
        Aluno alunoAtualizado = alunoService.atualizarAluno(AlunoMapper.INST.dtoToModel(formAluno), id);
        return AlunoMapper.INST.modelToDTO(alunoAtualizado);
    }

    @Operation(summary = "Altera a TURMA de um ou mais ALUNOS de uma vez")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/alterar-turma/{id}")
    public void alterarTurmaDeAlunos(@Valid @RequestBody AlunosListUUID formAlunos, @PathVariable Long id) {
        alunoService.alterarTurmaDeAlunos(formAlunos, id);
    }

    @Operation(summary = "Deleta um ou mais ALUNOS de uma vez")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void deletarAlunos(@Valid @RequestBody AlunosListUUID formAlunos) {
        alunoService.deletarAlunos(formAlunos);
    }

    @Operation(summary = "Gera a DECLARAÇÃO DE TRANSFERENCIA de um ALUNO")
    @GetMapping(value = "/{alunoId}/transferencia/imprimir", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> imprimirDeclaracaoTransferencia(@PathVariable UUID alunoId) {
        byte[] documentoBody = alunoService.imprimirDeclaracaoTransferencia(alunoId);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION,
                "inline; filename=declaracao_transferencia" + alunoId + ".pdf");
        header.setContentLength(documentoBody.length);

        return new ResponseEntity<>(documentoBody, header, HttpStatus.OK);
    }

}
