package br.com.sannicollas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentacaoRepository extends JpaRepository <br.com.sannicollas.model.Documentacao, Long> {
}
