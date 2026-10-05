package com.projetosAcademicos.domain.dto;

import com.projetosAcademicos.domain.model.Projeto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProjetoDTO {

    private Long id;
    private String titulo;
    private ProfessorDTO professorResponsavel;
    private String area;
    private String resumo;
    private String palavraChave1;
    private String palavraChave2;
    private String palavraChave3;
    private String urlDocumento;
    private List<AlunoDTO> alunos;

    public ProjetoDTO(Projeto p) {
        this.id = p.getId();
        this.titulo = p.getTitulo();
        this.professorResponsavel = p.getProfessorResponsavel() != null
                ? new ProfessorDTO(p.getProfessorResponsavel())
                : null;
        this.area = p.getArea();
        this.resumo = p.getResumo();
        this.palavraChave1 = p.getPalavraChave1();
        this.palavraChave2 = p.getPalavraChave2();
        this.palavraChave3 = p.getPalavraChave3();
        this.urlDocumento = p.getUrlDocumento();
        this.alunos = p.getAlunos() != null
                ? p.getAlunos().stream().map(AlunoDTO::new).collect(Collectors.toList())
                : new ArrayList<>();
    }
}