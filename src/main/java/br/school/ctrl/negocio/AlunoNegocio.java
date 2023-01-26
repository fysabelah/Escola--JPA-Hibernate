package br.school.ctrl.negocio;

import br.school.ctrl.exception.AlunoException;
import br.school.model.dao.AlunoDAO;
import br.school.model.entities.Aluno;

import java.util.List;

public class AlunoNegocio {

    private final AlunoDAO alunoDAO = new AlunoDAO();

    public Aluno inserir(Aluno aluno) throws AlunoException {
        if (aluno.getPessoa() == null || aluno.getCurso() == null) {
            throw new AlunoException("Pessoa e curso são itens necessários para criação de um(a) aluno(a)!");
        }

        return alunoDAO.inserir(aluno);
    }

    public List<Aluno> buscaTodos() throws AlunoException {
        List<Aluno> alunos = alunoDAO.buscarTodos();

        if (alunos == null || alunos.isEmpty()) {
            throw new AlunoException("Não existem alunos cadastrados");
        }

        return alunos;
    }

    public Aluno buscaPorId(Integer id) throws AlunoException {
        Aluno aluno = alunoDAO.buscarId(id);

        if (aluno != null) {
            return aluno;
        }

        throw new AlunoException("Aluno não encontrado");
    }

    public Aluno alterar(Aluno aluno) throws AlunoException {
        buscaPorId(aluno.getIdAluno());

        Aluno alunoCadastrado = alunoDAO.buscaPessoaPorIdAluno(aluno.getIdAluno());

        if (alunoCadastrado != null) {
            throw new AlunoException("Já existe um aluno cadastrado para esta pessoa!");
        }

        return alunoDAO.atualizar(aluno);
    }

    public void excluir(Integer id) throws AlunoException {
        Aluno aluno = buscaPorId(id);

        alunoDAO.excluir(aluno);
    }
}