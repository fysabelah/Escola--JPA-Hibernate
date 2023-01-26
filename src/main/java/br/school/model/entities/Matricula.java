package br.school.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "matricula")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Matricula implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idMatricula;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_aluno")
    private Aluno aluno;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_oferta")
    private Oferta oferta;

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Matrícula:")
                .append("\nIdentificação: ")
                .append(idMatricula)
                .append("\n")
                .append(aluno == null ? "Aluno:\n" : aluno.toString())
                .append(oferta == null ? "Oferta:\n" : oferta.toString())
                .toString();
    }
}
