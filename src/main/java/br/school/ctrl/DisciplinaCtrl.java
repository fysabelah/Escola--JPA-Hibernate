package br.school.ctrl;

import br.school.ctrl.exception.DisciplinaException;
import br.school.ctrl.negocio.DisciplinaNegocio;
import br.school.model.entities.Disciplina;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DisciplinaCtrl {

    private final DisciplinaNegocio negocio = new DisciplinaNegocio();

    private static final Logger LOGGER = Logger.getLogger(DisciplinaCtrl.class.getName());

    public Disciplina inserir(Disciplina disciplina) {
        try {
            disciplina = negocio.inserir(disciplina);
            LOGGER.log(Level.INFO, "Disciplina cadastrada com sucesso: {0}", disciplina);
        } catch (DisciplinaException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao tentar cadastrar disciplina: {0}", e.getMessage());
        }

        return disciplina;
    }

    public List<Disciplina> buscaTodos() {
        List<Disciplina> disciplinas = new ArrayList<>();

        try {
            disciplinas = negocio.buscaTodos();
        } catch (DisciplinaException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro tentar buscar as disciplinas cadastradas: {0}", e.getMessage());
        }

        return disciplinas;
    }

    public List<Disciplina> buscaPorNome(String disciplina) {
        List<Disciplina> disciplinas = new ArrayList<>();

        try {
            disciplinas = negocio.buscarPorNome(disciplina);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro tentar buscar as disciplinas cadastradas: {0}", e.getMessage());
        }

        return disciplinas;
    }

    public Disciplina buscaPorId(Integer id) {
        Disciplina disciplina = null;

        try {
            disciplina = negocio.buscaPorId(id);
        } catch (DisciplinaException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro tentar buscar disciplina: {0}", e.getMessage());
        }

        return disciplina;
    }

    public Disciplina alterar(Disciplina disciplina) {
        try {
            disciplina = negocio.alterar(disciplina);
            LOGGER.log(Level.INFO, "Disciplina alterada com sucesso: {0}", disciplina);
        } catch (DisciplinaException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao tentar alterar disciplina: {0}", e.getMessage());
        }

        return disciplina;
    }

    public void excluir(Integer id) {
        try {
            negocio.excluir(id);
            LOGGER.log(Level.INFO, "Disciplina excluída com sucesso");
        } catch (DisciplinaException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao tentar excluir a disciplina: {0}", e.getMessage());
        }
    }
}
