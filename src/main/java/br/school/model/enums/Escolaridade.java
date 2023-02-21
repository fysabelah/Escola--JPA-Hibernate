package br.school.model.enums;

public enum Escolaridade {

    MEDIO("Médio"),
    GRADUACAO("Graduação"),
    ESPECIALIZACAO("Especialização"),
    MESTRADO("Mestrado"),
    DOUTORADO("Doutorado");

    private String tipo;

    Escolaridade(String tipo) {
        this.tipo = tipo;
    }

    public static Escolaridade getType(String value) {
        for (Escolaridade escolaridade : Escolaridade.values()) {
            if (escolaridade.tipo.equals(value)) {
                return escolaridade;
            }
        }

        return null;
    }
}
