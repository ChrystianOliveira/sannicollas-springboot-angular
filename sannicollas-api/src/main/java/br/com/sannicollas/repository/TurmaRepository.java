package br.com.sannicollas.repository;

import br.com.sannicollas.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository <Turma, Long>, JpaSpecificationExecutor<Turma> {
}
