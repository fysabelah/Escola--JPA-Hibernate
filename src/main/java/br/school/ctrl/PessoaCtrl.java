package br.school.ctrl;

import br.school.ctrl.exception.PessoaException;
import br.school.ctrl.negocio.PessoaNegocio;
import br.school.model.entities.Pessoa;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PessoaCtrl {

    private final PessoaNegocio negocio = new PessoaNegocio();

    private static final Logger LOGGER = Logger.getLogger(PessoaCtrl.class.getName());

    public Pessoa inserir(Pessoa pessoa) {
        try {
            pessoa = negocio.inserir(pessoa);
            LOGGER.log(Level.INFO, "Pessoa cadastrada com sucesso: {0}", pessoa);
        } catch (PessoaException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Erro ao tentar cadastrar pessoa: {0}", e.getMessage());
        }

        return pessoa;
    }

    public List<Pessoa> buscaTodos() {
        List<Pessoa> pessoas = new ArrayList<>();

        try {
            pessoas = negocio.buscaTodos();
        } catch (PessoaException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Erro tentar buscar as pessoas cadastradas: {0}", e.getMessage());
        }

        return pessoas;
    }

    public Pessoa buscaPorId(Integer id) {
        Pessoa pessoa = null;

        try {
            pessoa = negocio.buscaPorId(id);
        } catch (PessoaException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Erro tentar buscar pessoa: {0}", e.getMessage());
        }

        return pessoa;
    }

    public Pessoa alterar(Pessoa pessoa) {
        try {
            pessoa = negocio.alterar(pessoa);
            LOGGER.log(Level.INFO, "Pessoa alterada com sucesso: {0}", pessoa);
        } catch (PessoaException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Erro ao tentar alterar pessoa: {0}", e.getMessage());
        }

        return pessoa;
    }

    public void excluir(Integer id) {
        try {
            negocio.excluir(id);
            LOGGER.log(Level.INFO, "Pessoa excluída com sucesso");
        } catch (PessoaException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        } catch (Exception e) {
            LOGGER.log(Level.INFO, "Erro ao tentar excluir a pessoa: {0}", e.getMessage());
        }
    }
}
