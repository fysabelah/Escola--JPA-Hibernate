package br.school.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "aluno")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idAluno;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "ativo")
    private Boolean ativo;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Aluno:")
                .append("\nIdentificação: ")
                .append(idAluno)
                .append("\nData Início: ")
                .append(dataInicio)
                .append("\nAtivo: ")
                .append(Boolean.TRUE.equals(ativo) ? "Sim" : "Não")
                .append("\n")
                .append(pessoa == null ? "Pessoa:\n" : pessoa.toString())
                .append("\n")
                .append(curso == null ? "Curso:\n" : curso.toString())
                .toString();
    }
}
