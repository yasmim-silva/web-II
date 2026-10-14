package com.example.projeto.domain.service;

import com.example.projeto.client.AlunoClient;
import com.example.projeto.client.ProfessorClient;
import com.example.projeto.client.ServicoIndisponivelException;
import com.example.projeto.domain.dto.AlunoDTO;
import com.example.projeto.domain.dto.ProfessorDTO;
import com.example.projeto.domain.dto.ProjetoDTO;
import com.example.projeto.domain.model.Projeto;
import com.example.projeto.domain.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ProfessorClient professorClient;

    @Autowired
    private AlunoClient alunoClient;

    public List<ProjetoDTO> getProjetos() {
        return projetoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Optional<ProjetoDTO> getProjetoById(Long id) {
        return projetoRepository.findById(id).map(this::toDTO);
    }

    public Projeto cadastrar(ProjetoDTO dados) {
        Projeto projeto = new Projeto();
        preencher(projeto, dados);
        return projetoRepository.save(projeto);
    }

    public Projeto atualizar(ProjetoDTO dados, Long id) {
        Optional<Projeto> optional = projetoRepository.findById(id);
        if (optional.isPresent()) {
            Projeto projetoBD = optional.get();
            preencher(projetoBD, dados);

            projetoRepository.save(projetoBD);
            return projetoBD;
        } else {
            throw new RuntimeException("Não foi possível atualizar o projeto informado");
        }
    }

    public void remover(Long id) {
        Optional<Projeto> projeto = projetoRepository.findById(id);
        if (projeto.isPresent()) {
            projetoRepository.deleteById(id);
        }
    }

    private void preencher(Projeto projeto, ProjetoDTO dados) {
        projeto.setTitulo(dados.getTitulo());
        projeto.setProfessorResponsavelId(validarProfessor(dados.getProfessorResponsavel()));
        projeto.setArea(dados.getArea());
        projeto.setResumo(dados.getResumo());
        projeto.setPalavraChave1(dados.getPalavraChave1());
        projeto.setPalavraChave2(dados.getPalavraChave2());
        projeto.setPalavraChave3(dados.getPalavraChave3());
        projeto.setUrlDocumento(dados.getUrlDocumento());
        projeto.setAlunosIds(validarAlunos(dados.getAlunos()));
    }

    /** Equivalente ao antigo buscarProfessor: confere no serviço de professores se o ID existe. */
    private Long validarProfessor(ProfessorDTO professor) {
        if (professor == null || professor.getId() == null) {
            return null;
        }
        professorClient.buscarPorId(professor.getId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado: " + professor.getId()));
        return professor.getId();
    }

    /** Equivalente ao antigo buscarAlunos: confere no serviço de alunos se cada ID existe. */
    private List<Long> validarAlunos(List<AlunoDTO> alunos) {
        if (alunos == null) {
            return new ArrayList<>();
        }
        return alunos.stream()
                .map(aluno -> {
                    if (aluno == null || aluno.getId() == null) {
                        throw new RuntimeException("Aluno não encontrado: null");
                    }
                    alunoClient.buscarPorId(aluno.getId())
                            .orElseThrow(() -> new RuntimeException("Aluno não encontrado: " + aluno.getId()));
                    return aluno.getId();
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Monta a resposta com os dados completos de professor e alunos, buscados
     * nos outros serviços. Se um registro não existir mais ou o serviço estiver
     * fora do ar, devolve apenas o ID, para que a consulta de projetos não falhe.
     */
    private ProjetoDTO toDTO(Projeto p) {
        ProfessorDTO professor = null;
        if (p.getProfessorResponsavelId() != null) {
            professor = buscarProfessorOuReferencia(p.getProfessorResponsavelId());
        }
        List<AlunoDTO> alunos = p.getAlunosIds() == null ? new ArrayList<>()
                : p.getAlunosIds().stream().map(this::buscarAlunoOuReferencia).collect(Collectors.toList());
        return new ProjetoDTO(p, professor, alunos);
    }

    private ProfessorDTO buscarProfessorOuReferencia(Long id) {
        try {
            return professorClient.buscarPorId(id).orElseGet(() -> new ProfessorDTO(id));
        } catch (ServicoIndisponivelException e) {
            return new ProfessorDTO(id);
        }
    }

    private AlunoDTO buscarAlunoOuReferencia(Long id) {
        try {
            return alunoClient.buscarPorId(id).orElseGet(() -> new AlunoDTO(id));
        } catch (ServicoIndisponivelException e) {
            return new AlunoDTO(id);
        }
    }
}
