package com.example.projeto.domain.dto;

import com.example.projeto.domain.model.Projeto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Mesmo formato JSON do ProjetoDTO original. É usado tanto na resposta
 * (com professor e alunos completos, obtidos via REST) quanto no corpo de
 * POST/PUT, que continua aceitando {"professorResponsavel": {"id": 1},
 * "alunos": [{"id": 2}]} como na API monolítica.
 */
@Data
@NoArgsConstructor
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

    public ProjetoDTO(Projeto p, ProfessorDTO professorResponsavel, List<AlunoDTO> alunos) {
        this.id = p.getId();
        this.titulo = p.getTitulo();
        this.professorResponsavel = professorResponsavel;
        this.area = p.getArea();
        this.resumo = p.getResumo();
        this.palavraChave1 = p.getPalavraChave1();
        this.palavraChave2 = p.getPalavraChave2();
        this.palavraChave3 = p.getPalavraChave3();
        this.urlDocumento = p.getUrlDocumento();
        this.alunos = alunos != null ? alunos : new ArrayList<>();
    }
}
