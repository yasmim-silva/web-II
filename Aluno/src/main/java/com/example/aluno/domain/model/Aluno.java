package com.example.aluno.domain.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "aluno")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Aluno {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "matricula")
	private String matricula;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "curso")
	private String curso;
	
	@NotNull()
	@OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "fk_endereco_id", foreignKey = @ForeignKey(name = "fk_endereco"), referencedColumnName = "id")
	private Endereco endereco;
	
}
