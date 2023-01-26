package br.school.model.dao;

import br.school.model.dao.util.GenericGenericDaoOperations;
import br.school.model.entities.Pessoa;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PessoaDAO extends GenericGenericDaoOperations<Pessoa> {

    private static final Logger LOGGER = Logger.getLogger(PessoaDAO.class.getName());

    public Pessoa buscarPorCpf(String cpf) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Pessoa> criteriaQuery = criteriaBuilder.createQuery(Pessoa.class);
        Root<Pessoa> root = criteriaQuery.from(Pessoa.class);

        criteriaQuery.select(root).where(criteriaBuilder.like(root.get("cpf"), cpf));

        Query query = getEntityManager().createQuery(criteriaQuery);

        Pessoa pessoa;

        try {
            pessoa = (Pessoa) query.getSingleResult();
        } catch (NoResultException exception) {
            LOGGER.log(Level.INFO, exception.getMessage());
            return null;
        } finally {
            getEntityManager().close();
        }

        return pessoa;
    }
}
