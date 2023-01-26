package br.school.model.enums;

public enum Dia {

    DOMINGO("Domingo"),
    SEGUNDA("Segunda-feira"),
    TERCA("Terça-feira"),
    QUARTA("Quarta-feira"),
    QUINTA("Quinta-feira"),
    SEXTA("Sexta-feira"),
    SABADO("Sábado");

    Dia(String value) {
        this.value = value;
    }

    private String value;

    public static Dia get(String value) {
        for (Dia dia : Dia.values()) {
            if (dia.value.equals(value)) {
                return dia;
            }
        }

        return null;
    }
}
