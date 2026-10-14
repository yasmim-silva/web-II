package com.example.projeto.client;

import com.example.projeto.domain.dto.AlunoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.Optional;

/**
 * Cliente REST do microsserviço de alunos. Substitui o acesso direto ao
 * AlunoRepository que o ProjetoService fazia na versão monolítica.
 */
@Component
public class AlunoClient {

    private final RestClient restClient;

    public AlunoClient(@Value("${servicos.aluno.url}") String baseUrl) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    /** Retorna vazio se o aluno não existir (404). */
    public Optional<AlunoDTO> buscarPorId(Long id) {
        try {
            AlunoDTO aluno = restClient.get()
                    .uri("/api/v1/alunos/{id}", id)
                    .retrieve()
                    .body(AlunoDTO.class);
            return Optional.ofNullable(aluno);
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        } catch (RestClientException e) {
            throw new ServicoIndisponivelException("Não foi possível consultar o serviço de alunos: " + e.getMessage(), e);
        }
    }
}
