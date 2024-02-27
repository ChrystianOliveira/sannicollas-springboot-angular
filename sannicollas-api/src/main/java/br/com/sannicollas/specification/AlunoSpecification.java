package br.com.sannicollas.specification;

import br.com.sannicollas.model.Aluno;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class AlunoSpecification {

    public static Specification<Aluno> porNomeAluno(String nome) {
        return Optional.ofNullable(nome)
                .filter(n -> !n.isEmpty())
                .map(n -> (Specification<Aluno>) (root, query, criteriaBuilder) -> {
                    String filtroLike = "%" + n.toUpperCase().replaceAll(" ", "%") + "%";
                    return criteriaBuilder.like(criteriaBuilder.upper(root.get("nome")), filtroLike);
                })
                .orElse((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
    }

    public static Specification<Aluno> porTurma(Long id) {
        return (root, query, criteriaBuilder) -> id == null ? null : criteriaBuilder.equal(root.get("turma").get("id"), id);
    }

}
