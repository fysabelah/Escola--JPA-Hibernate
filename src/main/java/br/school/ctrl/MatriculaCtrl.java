package br.school.ctrl;

import br.school.ctrl.exception.MatriculaException;
import br.school.ctrl.negocio.MatriculaNegocio;
import br.school.model.entities.Matricula;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MatriculaCtrl {
    private final MatriculaNegocio negocio = new MatriculaNegocio();

    private static final Logger LOGGER = Logger.getLogger(MatriculaCtrl.class.getName());

    public Matricula inserir(Matricula matricula) {
        try {
            matricula = negocio.inserir(matricula);
            LOGGER.log(Level.INFO, "Matrícula cadastrada com sucesso: {0}", matricula);
        } catch (MatriculaException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao tentar realizar matrícula: {0}", e.getMessage());
        }

        return matricula;
    }

    public List<Matricula> buscaTodos() {
        List<Matricula> matricula = new ArrayList<>();

        try {
            matricula = negocio.buscaTodos();
        } catch (MatriculaException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro tentar buscar matrículas cadastradas: {0}", e.getMessage());
        }

        return matricula;
    }

    public Matricula buscaPorId(Integer id) {
        Matricula matricula = null;

        try {
            matricula = negocio.buscaPorId(id);
        } catch (MatriculaException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro tentar buscar matrícula: {0}", e.getMessage());
        }

        return matricula;
    }

    public Matricula alterar(Matricula matricula) {
        try {
            matricula = negocio.alterar(matricula);
            LOGGER.log(Level.INFO, "Matrícula alterada com sucesso: {0}", matricula);
        } catch (MatriculaException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao tentar alterar matrícula: {0}", e.getMessage());
        }

        return matricula;
    }

    public void excluir(Integer id) {
        try {
            negocio.excluir(id);
            LOGGER.log(Level.INFO, "Matrícula excluída com sucesso");
        } catch (MatriculaException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao tentar excluir a matrícula: {0}", e.getMessage());
        }
    }
}
