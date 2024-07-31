package br.com.sannicollas.service;

import br.com.sannicollas.dto.TurmaFiltroDTO;
import br.com.sannicollas.exception.ObjectNotFoundException;
import br.com.sannicollas.model.Turma;
import br.com.sannicollas.repository.TurmaRepository;
import br.com.sannicollas.specification.TurmaSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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

        return turmaRepository.findAll(specification, pageable);
    }

    private static Specification<Turma> montarBuscarTurmaSpecification(TurmaFiltroDTO filtro) {
        return Specification
                .where(TurmaSpecification.porTurno(filtro.getTurno()))
                .and(TurmaSpecification.porTurmaNaoVazia());
    }
}
