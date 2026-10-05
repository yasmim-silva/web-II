package com.projetosAcademicos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "projeto")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", length = 255)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "fk_professor_id", foreignKey = @ForeignKey(name = "fk_projeto_professor"), referencedColumnName = "id")
    private Professor professorResponsavel;

    @Column(name = "area", length = 255)
    private String area;

    @Column(name = "resumo", length = 3000)
    private String resumo;

    @Column(name = "palavra_chave_1", length = 50)
    private String palavraChave1;

    @Column(name = "palavra_chave_2", length = 50)
    private String palavraChave2;

    @Column(name = "palavra_chave_3", length = 50)
    private String palavraChave3;

    @Column(name = "url_documento", length = 255)
    private String urlDocumento;

    @ManyToMany
    @JoinTable(
            name = "projeto_aluno",
            joinColumns = @JoinColumn(name = "fk_projeto_id", foreignKey = @ForeignKey(name = "fk_projeto_aluno_projeto")),
            inverseJoinColumns = @JoinColumn(name = "fk_aluno_id", foreignKey = @ForeignKey(name = "fk_projeto_aluno_aluno"))
    )
    private List<Aluno> alunos;
}