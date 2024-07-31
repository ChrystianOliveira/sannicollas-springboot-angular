package br.com.sannicollas.specification;

import br.com.sannicollas.model.Turma;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class TurmaSpecification {

    public static Specification<Turma> porTurno(Long id) {
        if (id != null && id == 0) {
            return null;
        }
        return (root, query, criteriaBuilder) -> id == null ? null : criteriaBuilder.equal(root.get("turno").get("id"), id);
    }

    public static Specification<Turma> porTurmaNaoVazia() {
        return (Root<Turma> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            return criteriaBuilder.isNotEmpty(root.get("alunos"));
        };
    }
}
