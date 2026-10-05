package com.projetosAcademicos.domain.repository;

import com.projetosAcademicos.domain.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    List<Professor> findByMatricula(Integer matricula);
}
