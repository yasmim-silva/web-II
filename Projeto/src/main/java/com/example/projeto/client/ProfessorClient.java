package com.example.projeto.client;

import com.example.projeto.domain.dto.ProfessorDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.Optional;

/**
 * Cliente REST do microsserviço de professores. Substitui o acesso direto ao
 * ProfessorRepository que o ProjetoService fazia na versão monolítica.
 */
@Component
public class ProfessorClient {

    private final RestClient restClient;

    public ProfessorClient(@Value("${servicos.professor.url}") String baseUrl) {
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    /** Retorna vazio se o professor não existir (404). */
    public Optional<ProfessorDTO> buscarPorId(Long id) {
        try {
            ProfessorDTO professor = restClient.get()
                    .uri("/api/v1/professores/{id}", id)
                    .retrieve()
                    .body(ProfessorDTO.class);
            return Optional.ofNullable(professor);
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        } catch (RestClientException e) {
            throw new ServicoIndisponivelException("Não foi possível consultar o serviço de professores: " + e.getMessage(), e);
        }
    }
}
