package com.projetosAcademicos.domain.dto;

import com.projetosAcademicos.domain.model.Endereco;
import com.projetosAcademicos.domain.model.Professor;
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
