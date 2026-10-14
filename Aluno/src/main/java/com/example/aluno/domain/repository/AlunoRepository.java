package com.example.aluno.domain.repository;

import java.util.List;

import com.example.aluno.domain.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	List<Aluno> findByMatricula(String matricula);
	

}
