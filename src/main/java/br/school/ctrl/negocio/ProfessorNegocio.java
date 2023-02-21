package br.school.ctrl.negocio;

import br.school.ctrl.exception.ProfessorException;
import br.school.model.dao.ProfessorDAO;
import br.school.model.entities.Professor;

import java.util.List;

public class ProfessorNegocio {

    private final ProfessorDAO professorDAO = new ProfessorDAO();

    public Professor inserir(Professor professor) throws ProfessorException {
        this.validarProfessor(professor);

        if (professor.getPessoa().getIdPessoa() != null) {
            Professor cadastrado = professorDAO.buscarPorIdCpfPessoa(professor.getPessoa().getCpf());

            if (cadastrado != null) {
                throw new ProfessorException("Está pessoa está associada a outro professor");
            }
        }

        return professorDAO.inserir(professor);
    }

    public List<Professor> buscaTodos() throws ProfessorException {
        List<Professor> professors = professorDAO.buscarTodos();

        if (professors == null || professors.isEmpty()) {
            throw new ProfessorException("Não existem professores cadastrados");
        }

        return professors;
    }

    public Professor buscaPorId(Integer id) throws ProfessorException {
        Professor professor = professorDAO.buscarId(id);

        if (professor == null) {
            throw new ProfessorException("Professor não encontrado!");
        }

        return professor;
    }

    public Professor alterar(Professor professor) throws ProfessorException {
        this.validarProfessor(professor);

        Professor professorCadastrado = buscaPorId(professor.getIdProfessor());

        professorCadastrado.setPessoa(professor.getPessoa());

        return professorDAO.atualizar(professorCadastrado);
    }

    public void excluir(Integer id) throws ProfessorException {
        Professor professor = buscaPorId(id);

        professorDAO.excluir(professor);
    }

    private void validarProfessor(Professor professor) throws ProfessorException {
        if (professor.getPessoa() == null && !professor.getPessoa().isPessoaValida()) {
            throw new ProfessorException("É necessário vincular uma pessoa ao professor!");
        }

        if (professor.getEscolaridade() == null) {
            throw new ProfessorException("É necessário informar o grau escolaridade!");
        }
    }
}
