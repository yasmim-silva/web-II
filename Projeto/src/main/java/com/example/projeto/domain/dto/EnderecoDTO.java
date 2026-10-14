package com.example.projeto.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representação do endereço recebido dos serviços de alunos e professores.
 * Não é entidade: o serviço de projetos não persiste endereços.
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnderecoDTO {

    private Long id;
    private String rua;
    private String numero;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;
}
