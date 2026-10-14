package com.example.projeto.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representação de um professor obtida do microsserviço de professores
 * (GET /api/v1/professores/{id}). Mesmos campos do ProfessorDTO original.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfessorDTO {

    private Long id;
    private Integer matricula;
    private String nome;
    private String curso;
    private EnderecoDTO endereco;

    public ProfessorDTO(Long id) {
        this.id = id;
    }
}
