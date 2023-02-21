package br.school.model.entities;

import br.school.model.enums.Dia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "oferta")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Oferta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_oferta")
    private Integer idOferta;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_professor")
    private Professor professor;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_disciplina")
    private Disciplina disciplina;

    @Column(name = "data_incio", columnDefinition = "DATE")
    private LocalDate dataInicio;

    @Column(name = "data_fim", columnDefinition = "DATE")
    private LocalDate dataFim;

    @Column(name = "id_dia")
    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Dia.class, fetch = FetchType.EAGER)
    private List<Dia> dias;

    @Column(name = "hora_inicio", columnDefinition = "TIME")
    private LocalTime horaInicial;

    @Column(name = "hora_fim", columnDefinition = "TIME")
    private LocalTime horaFinal;

    public boolean isOfertaValida() {
        boolean hasProfessorAndDisciplina = this.disciplina != null && this.professor != null;
        boolean hasData = this.dataFim != null && this.dataInicio != null;
        boolean hasHora = this.horaInicial != null && this.horaFinal != null;
        boolean hasDia = this.dias != null && !this.dias.isEmpty();

        return hasProfessorAndDisciplina && hasData && hasDia && hasHora;
    }

    @Override
    public String toString() {
        StringBuilder diasFormatacao = new StringBuilder();

        getDias().forEach(dia -> {
            diasFormatacao.append(dia.name());
            diasFormatacao.append("\n");
        });


        return new StringBuilder()
                .append("Oferta:\n")
                .append("Identificação: ")
                .append(idOferta)
                .append("\n")
                .append(professor == null ? "Professor:\n" : professor.toString())
                .append(disciplina == null ? "Disciplina:\n" :disciplina.toString())
                .append("\nData de Início: ")
                .append(dataInicio)
                .append("\nData de Finalização: ")
                .append(dataFim)
                .append("\nInicia-se a: ")
                .append(horaInicial)
                .append("\nTermina a: ")
                .append(horaFinal)
                .append("\n")
                .append("Dias:\n")
                .append(diasFormatacao)
                .toString();
    }
}
