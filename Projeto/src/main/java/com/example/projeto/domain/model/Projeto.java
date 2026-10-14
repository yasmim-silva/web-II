package com.example.projeto.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Projeto acadêmico.
 *
 * Na versão monolítica, o projeto tinha um @ManyToOne para Professor e um
 * @ManyToMany para Aluno. Como professores e alunos agora pertencem a outros
 * microsserviços (com bancos próprios), o projeto guarda apenas os
 * identificadores. As colunas e a tabela de junção mantêm os mesmos nomes do
 * esquema original (fk_professor_id e projeto_aluno), mas sem chaves
 * estrangeiras para tabelas de outros serviços.
 */
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

    /** ID do professor responsável no microsserviço de professores. */
    @Column(name = "fk_professor_id")
    private Long professorResponsavelId;

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

    /** IDs dos alunos participantes no microsserviço de alunos. */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "projeto_aluno",
            joinColumns = @JoinColumn(name = "fk_projeto_id", foreignKey = @ForeignKey(name = "fk_projeto_aluno_projeto"))
    )
    @Column(name = "fk_aluno_id")
    private List<Long> alunosIds = new ArrayList<>();
}
