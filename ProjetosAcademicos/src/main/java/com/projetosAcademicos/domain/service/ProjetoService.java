package com.projetosAcademicos.domain.service;

import com.projetosAcademicos.domain.dto.ProjetoDTO;
import com.projetosAcademicos.domain.model.Aluno;
import com.projetosAcademicos.domain.model.Professor;
import com.projetosAcademicos.domain.model.Projeto;
import com.projetosAcademicos.domain.repository.AlunoRepository;
import com.projetosAcademicos.domain.repository.ProfessorRepository;
import com.projetosAcademicos.domain.repository.ProjetoRepository;
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
    private ProfessorRepository professorRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    public List<ProjetoDTO> getProjetos() {
        return projetoRepository.findAll().stream().map(ProjetoDTO::new).collect(Collectors.toList());
    }

    public Optional<Projeto> getProjetoById(Long id) {
        return projetoRepository.findById(id);
    }

    public Projeto cadastrar(Projeto projeto) {
        projeto.setProfessorResponsavel(buscarProfessor(projeto.getProfessorResponsavel()));
        projeto.setAlunos(buscarAlunos(projeto.getAlunos()));
        return projetoRepository.save(projeto);
    }

    public Projeto atualizar(Projeto projeto, Long id) {
        Optional<Projeto> optional = getProjetoById(id);
        if (optional.isPresent()) {
            Projeto projetoBD = optional.get();
            projetoBD.setTitulo(projeto.getTitulo());
            projetoBD.setProfessorResponsavel(buscarProfessor(projeto.getProfessorResponsavel()));
            projetoBD.setArea(projeto.getArea());
            projetoBD.setResumo(projeto.getResumo());
            projetoBD.setPalavraChave1(projeto.getPalavraChave1());
            projetoBD.setPalavraChave2(projeto.getPalavraChave2());
            projetoBD.setPalavraChave3(projeto.getPalavraChave3());
            projetoBD.setUrlDocumento(projeto.getUrlDocumento());
            projetoBD.setAlunos(buscarAlunos(projeto.getAlunos()));

            projetoRepository.save(projetoBD);
            return projetoBD;
        } else {
            throw new RuntimeException("Não foi possível atualizar o projeto informado");
        }
    }

    public void remover(Long id) {
        Optional<Projeto> projeto = getProjetoById(id);
        if (projeto.isPresent()) {
            projetoRepository.deleteById(id);
        }
    }

    private Professor buscarProfessor(Professor professor) {
        if (professor == null || professor.getId() == null) {
            return null;
        }
        return professorRepository.findById(professor.getId())
                .orElseThrow(() -> new RuntimeException("Professor não encontrado: " + professor.getId()));
    }

    private List<Aluno> buscarAlunos(List<Aluno> alunos) {
        if (alunos == null) {
            return new ArrayList<>();
        }
        return alunos.stream()
                .map(aluno -> alunoRepository.findById(aluno.getId())
                        .orElseThrow(() -> new RuntimeException("Aluno não encontrado: " + aluno.getId())))
                .collect(Collectors.toList());
    }
}