package com.projetosAcademicos.domain.repository;

import com.projetosAcademicos.domain.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
