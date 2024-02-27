package br.com.sannicollas.service;

import br.com.sannicollas.dto.TurmaFiltroDTO;
import br.com.sannicollas.exception.ObjectNotFoundException;
import br.com.sannicollas.model.Turma;
import br.com.sannicollas.repository.TurmaRepository;
import br.com.sannicollas.specification.TurmaSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TurmaService {

    private final TurmaRepository turmaRepository;

    public Turma buscarPorId(Long id) {
        return turmaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Turma com ID " + id + " não encontrado."));
    }

    public Page<Turma> buscarTurmas(Integer page, Integer size, TurmaFiltroDTO filtro) {

        Pageable pageable = PageRequest.of(page, size);

        Specification<Turma> specification = this.montarBuscarTurmaSpecification(filtro);
        Page<Turma> turmas = turmaRepository.findAll(specification, pageable);
        List<Turma> turmaList = turmas.stream().collect(Collectors.toList());
        turmaList.removeIf(turma -> turma.getAlunos().isEmpty());
        Page<Turma> turmasComAluno = new PageImpl<>(turmaList, turmas.getPageable(), turmas.getTotalElements());

        return  turmasComAluno;
    }

    private static Specification<Turma> montarBuscarTurmaSpecification(TurmaFiltroDTO filtro) {
        return Specification
                .where(TurmaSpecification.porTurno(filtro.getTurno()));
    }

}
