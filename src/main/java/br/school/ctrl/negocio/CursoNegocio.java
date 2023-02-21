package br.school.ctrl.negocio;

import br.school.ctrl.exception.CursoException;
import br.school.model.dao.CursoDAO;
import br.school.model.entities.Curso;

import java.util.List;

public class CursoNegocio {
    private final CursoDAO cursoDAO = new CursoDAO();

    public Curso inserir(Curso curso) throws CursoException {
        this.validarCurso(curso);

        List<Curso> jaCadastrado = cursoDAO.buscarPorNomeIdentico(curso.getNomeCurso());

        if (!jaCadastrado.isEmpty()) {
            throw new CursoException("Curso já cadastrado!");
        }

        return cursoDAO.inserir(curso);
    }

    public List<Curso> buscaTodos() throws CursoException {
        List<Curso> cursos = cursoDAO.buscarTodos();

        if (cursos == null || cursos.isEmpty()) {
            throw new CursoException("Não existem cursos cadastrados");
        }

        return cursos;
    }

    public Curso buscaPorId(Integer id) throws CursoException {
        Curso curso = cursoDAO.buscarId(id);

        if (curso == null) {
            throw new CursoException("Curso não encontrado");
        }

        return curso;
    }

    public Curso alterar(Curso curso) throws CursoException {
        this.validarCurso(curso);

        buscaPorId(curso.getIdCurso());

        return cursoDAO.atualizar(curso);
    }

    public void excluir(Integer id) throws CursoException {
        Curso curso = buscaPorId(id);

        cursoDAO.excluir(curso);
    }

    private void validarCurso(Curso curso) throws CursoException {
        if (curso == null || curso.getNomeCurso() == null || curso.getNomeCurso().trim().isEmpty()) {
            throw new CursoException("É necessário informar o nome do curso.");
        }
    }

    public List<Curso> buscarPorNome(String nomeCurso) throws CursoException {
        List<Curso> curso = cursoDAO.buscarPorNome(nomeCurso);

        if (curso == null) {
            throw new CursoException("Curso não encontrado");
        }

        return curso;
    }
}
