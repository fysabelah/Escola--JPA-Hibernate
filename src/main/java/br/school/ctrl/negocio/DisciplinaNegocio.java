package br.school.ctrl.negocio;

import br.school.ctrl.exception.DisciplinaException;
import br.school.model.dao.DisciplinaDAO;
import br.school.model.entities.Disciplina;

import java.util.List;

public class DisciplinaNegocio {

    private final DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

    public Disciplina inserir(Disciplina disciplina) throws DisciplinaException {
        this.validarDisciplina(disciplina);

        List<Disciplina> disciplinasCadastradas = disciplinaDAO.buscarPorNomeEquals(disciplina.getNomeDisciplina());

        if (!disciplinasCadastradas.isEmpty()) {
            throw new DisciplinaException("Disciplina já cadastrada");
        }

        return disciplinaDAO.inserir(disciplina);
    }

    public List<Disciplina> buscaTodos() throws DisciplinaException {
        List<Disciplina> disciplinas = disciplinaDAO.buscarTodos();

        if (disciplinas == null || disciplinas.isEmpty()) {
            throw new DisciplinaException("Não existem disciplinas cadastradas");
        }

        return disciplinaDAO.buscarTodos();
    }

    public Disciplina buscaPorId(Integer id) throws DisciplinaException {
        Disciplina disciplina = disciplinaDAO.buscarId(id);

        if (disciplina == null) {
            throw new DisciplinaException("Disciplina não encontrada");
        }

        return disciplina;
    }

    public Disciplina alterar(Disciplina disciplina) throws DisciplinaException {
        this.validarDisciplina(disciplina);

        buscaPorId(disciplina.getIdDisciplina());

        return disciplinaDAO.atualizar(disciplina);
    }

    public void excluir(Integer id) throws DisciplinaException {
        Disciplina disciplina = buscaPorId(id);

        disciplinaDAO.excluir(disciplina);
    }

    public List<Disciplina> buscarPorNome(String str) {
        return disciplinaDAO.buscarPorNomeLike(str);
    }

    private void validarDisciplina(Disciplina disciplina) throws DisciplinaException {
        if (disciplina.getCargaHoraria() <= 0) {
            throw new DisciplinaException("Carga horária deve ser maior que 0.");
        }

        if (disciplina.getNomeDisciplina() == null || disciplina.getNomeDisciplina().trim().isEmpty()) {
            throw new DisciplinaException("Nome da disciplina é obrigatório.");
        }
    }
}
