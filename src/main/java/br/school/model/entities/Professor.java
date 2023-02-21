package br.school.model.entities;

import br.school.model.enums.Escolaridade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "professor")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Professor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idProfessor;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Enumerated(EnumType.STRING)
    private Escolaridade escolaridade;

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Professor:\n")
                .append("Identificação: ")
                .append(idProfessor)
                .append("\n")
                .append(pessoa == null ? "Pessoa:\n" : pessoa.toString())
                .append("\nEscolaridade: ")
                .append(escolaridade.name())
                .append("\n")
                .toString();
    }
}
