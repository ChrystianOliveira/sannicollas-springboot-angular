package br.com.sannicollas.repository;

import br.com.sannicollas.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlunoRepository extends JpaRepository <Aluno, UUID>, JpaSpecificationExecutor<Aluno> {
}
