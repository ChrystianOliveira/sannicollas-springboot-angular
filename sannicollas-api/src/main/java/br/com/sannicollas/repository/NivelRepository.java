package br.com.sannicollas.repository;

import br.com.sannicollas.model.Nivel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NivelRepository extends JpaRepository <Nivel, Long> {
}
