package br.school.ctrl;

import br.school.ctrl.exception.AlunoException;
import br.school.ctrl.negocio.AlunoNegocio;
import br.school.model.entities.Aluno;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlunoCtrl {
    private final AlunoNegocio negocio = new AlunoNegocio();

    private static final Logger LOGGER = Logger.getLogger(AlunoCtrl.class.getName());

    public Aluno inserir(Aluno aluno) {
        try {
            aluno = negocio.inserir(aluno);
            LOGGER.log(Level.INFO, "Aluno(a) cadastrado(a) com sucesso: {0}", aluno);
        } catch (AlunoException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao tentar cadastrar aluno(a): {0}", e.getMessage());
        }

        return aluno;
    }

    public List<Aluno> buscaTodos() {
        List<Aluno> aluno = new ArrayList<>();

        try {
            aluno = negocio.buscaTodos();
        } catch (AlunoException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro tentar buscar os alunos cadastrados: {0}", e.getMessage());
        }

        return aluno;
    }

    public Aluno buscaPorId(Integer id) {
        Aluno aluno = null;

        try {
            aluno = negocio.buscaPorId(id);
        } catch (AlunoException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro tentar buscar o(a) aluno(a): {0}", e.getMessage());
        }

        return aluno;
    }

    public Aluno alterar(Aluno aluno) {
        try {
            aluno = negocio.alterar(aluno);
            LOGGER.log(Level.INFO, "Aluno atualizado com sucesso: {0}", aluno);
        } catch (AlunoException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao tentar atualizar o(a) aluno(a): {0}", e.getMessage());
        }

        return aluno;
    }

    public void excluir(Integer id) {
        try {
            negocio.excluir(id);
            LOGGER.log(Level.INFO, "Aluno(a) excluído(a) com sucesso");
        } catch (AlunoException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao tentar excluir o(a) aluno(a): {0}", e.getMessage());
        }
    }
}
