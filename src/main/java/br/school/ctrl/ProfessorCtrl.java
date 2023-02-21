package br.school.ctrl;

import br.school.ctrl.exception.ProfessorException;
import br.school.ctrl.negocio.ProfessorNegocio;
import br.school.model.entities.Professor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfessorCtrl {

    private final ProfessorNegocio negocio = new ProfessorNegocio();

    private static final Logger LOGGER = Logger.getLogger(ProfessorCtrl.class.getName());

    public Professor inserir(Professor professor) {
        try {
            professor = negocio.inserir(professor);
            LOGGER.log(Level.INFO, "Professor cadastrado com sucesso: {0}", professor);
        } catch (ProfessorException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Erro ao tentar cadastrar professor: {0}", e.getMessage());
        }

        return professor;
    }

    public List<Professor> buscaTodos() {
        List<Professor> professors = new ArrayList<>();

        try {
            professors = negocio.buscaTodos();
        } catch (ProfessorException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Erro tentar buscar as professores cadastrados: {0}", e.getMessage());
        }

        return professors;
    }

    public Professor buscaPorId(Integer id) {
        Professor professor = null;

        try {
            professor = negocio.buscaPorId(id);
        } catch (ProfessorException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Erro tentar buscar professor: {0}", e.getMessage());
        }

        return professor;
    }

    public Professor alterar(Professor professor) {
        try {
            professor = negocio.alterar(professor);
            LOGGER.log(Level.INFO, "Professor alterado com sucesso: {0}", professor);
        } catch (ProfessorException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Erro ao tentar alterar professor: {0}", e.getMessage());
        }

        return professor;
    }

    public void excluir(Integer id) {
        try {
            negocio.excluir(id);
            LOGGER.log(Level.INFO, "Professor excluída com sucesso");
        } catch (ProfessorException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Erro ao tentar excluir o professor: {0}", e.getMessage());
        }
    }
}
