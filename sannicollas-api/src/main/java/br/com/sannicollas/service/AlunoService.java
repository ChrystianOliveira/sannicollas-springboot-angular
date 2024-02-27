package br.com.sannicollas.service;

import br.com.sannicollas.dto.AlunoFiltroDTO;
import br.com.sannicollas.dto.AlunosListUUID;
import br.com.sannicollas.exception.ObjectNotFoundException;
import br.com.sannicollas.exception.SanNicollasException;
import br.com.sannicollas.model.Aluno;
import br.com.sannicollas.model.Turma;
import br.com.sannicollas.relatorio.RelatorioService;
import br.com.sannicollas.repository.AlunoRepository;
import br.com.sannicollas.specification.AlunoSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository alunoRepository;

    private final TurmaService turmaService;

    private final RelatorioService relatorioService;

    public Aluno buscarPorId(UUID id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Aluno com ID " + id + " não encontrado."));
    }

    public Page<Aluno> buscarAlunos(Integer page, Integer size, AlunoFiltroDTO filtro) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "nome"));

        Specification<Aluno> specification = this.montarBuscarAlunoSpecification(filtro);

        return alunoRepository.findAll(specification, pageable);
    }

    public Aluno cadastrarAluno(Aluno aluno) {
        aluno.setId(null);
        alunoRepository.save(aluno);
        Turma turma = turmaService.buscarPorId(aluno.getTurma().getId());
        Aluno alunoDB = this.buscarPorId(aluno.getId());
        alunoDB.setTurma(turma);
        return alunoDB;
    }

    private static Specification<Aluno> montarBuscarAlunoSpecification(AlunoFiltroDTO filtro) {
        return Specification
                .where(AlunoSpecification.porNomeAluno(filtro.getNome())
                        .and(AlunoSpecification.porTurma(filtro.getTurma())));
    }

    public Aluno atualizarAluno(Aluno formAluno, UUID id) {
        Aluno alunoAtualizado = this.buscarPorId(id);
        BeanUtils.copyProperties(formAluno, alunoAtualizado, "id");
        return alunoRepository.save(alunoAtualizado);
    }

    @Transactional
    public void alterarTurmaDeAlunos(AlunosListUUID formAlunos, Long turmaId) {
        Turma turma = turmaService.buscarPorId(turmaId);

        List<Aluno> alunos = formAlunos.getAlunosIds()
                .stream()
                .map(id -> this.buscarPorId(id))
                .collect(Collectors.toList());

        if (alunos.stream().allMatch(aluno -> turma.equals(aluno.getTurma()))) {
            throw new SanNicollasException("Todos os alunos já pertencem à turma informada. ID: " + turmaId.toString());
        }

        alunos.forEach(aluno -> aluno.setTurma(turma));
        alunoRepository.saveAll(alunos);
    }

    public void deletarAlunos(AlunosListUUID formAlunos) {
        List<Aluno> alunos = formAlunos.getAlunosIds()
                .stream()
                .map(id -> this.buscarPorId(id))
                .collect(Collectors.toList());

        alunoRepository.deleteAll(alunos);
    }

    public byte[] imprimirDeclaracaoTransferencia (UUID alunoId) {
        Optional<Aluno> aluno = alunoRepository.findById(alunoId);
        if(aluno.isEmpty()) {
            throw new SanNicollasException("Não foi possível gerar declaração, aluno não existe");
        }

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("ALUNO_ID", alunoId);
        parametros.put("LOGO", relatorioService.getImagesPath("logo.png"));

        String relatorioPath = relatorioService.getReportsPath("declaracao_transferencia.jrxml");

        return relatorioService.gerarRelatorioJrxml(parametros, relatorioPath);
    }

}
