package com.example.projeto.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representação de um aluno obtida do microsserviço de alunos
 * (GET /api/v1/alunos/{id}). Mesmos campos do AlunoDTO original.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlunoDTO {

    private Long id;
    private String nome;
    private String matricula;
    private String cpf;
    private String curso;
    private EnderecoDTO endereco;

    public AlunoDTO(Long id) {
        this.id = id;
    }
}
