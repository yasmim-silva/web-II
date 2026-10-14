package com.example.professor.domain.dto;

import com.example.professor.domain.model.Endereco;
import com.example.professor.domain.model.Professor;
import lombok.Data;

@Data
public class ProfessorDTO {

    private Long id;
    private Integer matricula;
    private String nome;
    private String curso;
    private Endereco endereco;

    public ProfessorDTO(Professor p) {
        this.id = p.getId();
        this.matricula = p.getMatricula();
        this.nome = p.getNome();
        this.curso = p.getCurso();
        this.endereco = p.getEndereco();
    }
}
