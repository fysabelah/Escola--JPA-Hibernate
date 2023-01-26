package br.school.ctrl.negocio;

import br.school.ctrl.exception.PessoaException;
import br.school.model.dao.PessoaDAO;
import br.school.model.entities.Pessoa;

import java.util.List;

public class PessoaNegocio {

    private final PessoaDAO pessoaDAO = new PessoaDAO();

    public Pessoa inserir(Pessoa pessoa) throws PessoaException {
        validarPessoa(pessoa);

        Pessoa pessoaCadastrada = pessoaDAO.buscarPorCpf(pessoa.getCpf());

        if (pessoaCadastrada != null) {
            throw new PessoaException("Já existe uma pessoa cadastrada com esse CPF.");
        }

        return pessoaDAO.inserir(pessoa);
    }

    public List<Pessoa> buscaTodos() throws PessoaException {
        List<Pessoa> pessoas = pessoaDAO.buscarTodos();

        if (pessoas == null || pessoas.isEmpty()) {
            throw new PessoaException("Não existem pessoas cadastradas");
        }

        return pessoas;
    }

    public Pessoa buscaPorId(Integer id) throws PessoaException {
        Pessoa pessoa = pessoaDAO.buscarId(id);

        if (pessoa == null) {
            throw new PessoaException("Pessoa não encontrada!");
        }

        return pessoa;
    }

    public Pessoa alterar(Pessoa pessoa) throws PessoaException {
        validarPessoa(pessoa);

        Pessoa pessoaCadastrada = buscaPorId(pessoa.getIdPessoa());

        pessoa.setCpf(pessoaCadastrada.getCpf());

        return pessoaDAO.atualizar(pessoa);
    }

    public void excluir(Integer id) throws PessoaException {
        Pessoa pessoa = buscaPorId(id);

        pessoaDAO.excluir(pessoa);
    }

    private void validarPessoa(Pessoa pessoa) throws PessoaException {
        if (!pessoa.isPessoaValida()) {
            throw new PessoaException("Existem informações a serem preenchidas");
        }
    }
}
