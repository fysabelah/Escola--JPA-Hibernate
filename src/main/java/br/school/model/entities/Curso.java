package br.school.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "curso")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idCurso;

    @Column(name = "nome")
    private String nomeCurso;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "curso_disciplinas")
    private Set<Disciplina> disciplinas;

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Curso:")
                .append("\nIdentificação: ")
                .append(idCurso)
                .append("\nNome: ")
                .append(nomeCurso)
                .toString();
    }
}
