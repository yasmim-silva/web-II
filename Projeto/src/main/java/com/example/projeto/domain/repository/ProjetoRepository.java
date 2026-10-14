package com.example.projeto.domain.repository;

import com.example.projeto.domain.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
