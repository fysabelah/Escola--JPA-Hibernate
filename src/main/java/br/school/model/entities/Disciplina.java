package br.school.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "disciplina")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Disciplina implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idDisciplina;

    @Column(name = "nome")
    private String nomeDisciplina;

    @Column(name = "carga_horaria")
    private Integer cargaHoraria;

    @Override
    public String toString() {
        return new StringBuilder("Disciplina:")
                .append("\nIdentificação: ")
                .append(idDisciplina)
                .append("\nNome: ")
                .append(nomeDisciplina)
                .append("\nCarga Horária: ")
                .append(cargaHoraria)
                .toString();
    }
}
