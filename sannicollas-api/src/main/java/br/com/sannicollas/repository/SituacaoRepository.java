package br.com.sannicollas.repository;

import br.com.sannicollas.model.Situacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituacaoRepository extends JpaRepository <Situacao, Long> {
}
