package br.school.ctrl;

import br.school.ctrl.exception.CursoException;
import br.school.ctrl.negocio.CursoNegocio;
import br.school.model.entities.Curso;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CursoCtrl {
    private final CursoNegocio negocio = new CursoNegocio();

    private static final Logger LOGGER = Logger.getLogger(CursoCtrl.class.getName());

    public Curso inserir(Curso curso) {
        try {
            curso = negocio.inserir(curso);
            LOGGER.log(Level.INFO, "Curso cadastrada com sucesso: {0}", curso);
        } catch (CursoException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao tentar cadastrar curso: {0}", e.getMessage());
        }

        return curso;
    }

    public List<Curso> buscaTodos() {
        List<Curso> curso = new ArrayList<>();

        try {
            curso = negocio.buscaTodos();
        } catch (CursoException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro tentar buscar os cursos cadastrados: {0}", e.getMessage());
        }

        return curso;
    }

    public Curso buscaPorId(Integer id) {
        Curso curso = null;

        try {
            curso = negocio.buscaPorId(id);
        } catch (CursoException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro tentar buscar o curso: {0}", e.getMessage());
        }

        return curso;
    }

    public Curso alterar(Curso curso) {
        try {
            curso = negocio.alterar(curso);
            LOGGER.log(Level.INFO, "Curso alterada com sucesso: {0}", curso);
        } catch (CursoException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao tentar atualizar curso: {0}", e.getMessage());
        }

        return curso;
    }

    public void excluir(Integer id) {
        try {
            negocio.excluir(id);
            LOGGER.log(Level.INFO, "Curso excluído com sucesso!");
        } catch (CursoException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao tentar excluir o curso");
        }
    }

    public List<Curso> buscarPorNome(String nomeCurso) {
        List<Curso> cursos = new ArrayList<>();

        try {
            cursos = negocio.buscarPorNome(nomeCurso);
        } catch (CursoException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro tentar buscar o curso: {0}", e.getMessage());
        }

        return cursos;
    }
}
