package com.projetosAcademicos.domain.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "professor")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "matricula")
    private Integer matricula;

    @Column(name = "nome", length = 255)
    private String nome;

    @Column(name = "curso", length = 255)
    private String curso;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_endereco_id", foreignKey = @ForeignKey(name = "fk_professor_endereco"), referencedColumnName = "id")
    private Endereco endereco;
}
