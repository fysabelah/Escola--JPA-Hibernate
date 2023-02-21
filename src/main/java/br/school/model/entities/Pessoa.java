package br.school.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "pessoa")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idPessoa;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "data_nascimento", columnDefinition = "DATE")
    private LocalDate dataNascimento;

    public boolean isPessoaValida() {
        boolean hasNomeValido = this.nome != null && !this.nome.trim().isEmpty();
        boolean hasCpf = this.cpf != null && !this.cpf.trim().isEmpty();

        return hasCpf && hasNomeValido && this.dataNascimento != null;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Pessoas:\n")
                .append("Identificação: ")
                .append(idPessoa)
                .append("\nNome: ")
                .append(nome)
                .append("\nDocumento de Identificação: ")
                .append(cpf)
                .append("\nData de Nascimento: ")
                .append(dataNascimento)
                .toString();
    }
}
