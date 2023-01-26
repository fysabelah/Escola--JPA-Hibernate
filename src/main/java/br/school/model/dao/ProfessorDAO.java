package br.school.model.dao;

import br.school.model.dao.util.GenericGenericDaoOperations;
import br.school.model.entities.Professor;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfessorDAO extends GenericGenericDaoOperations<Professor> {

    private static final Logger LOGGER = Logger.getLogger(ProfessorDAO.class.getName());

    public Professor buscarPorIdCpfPessoa(String cpf) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Professor> criteriaQuery = criteriaBuilder.createQuery(Professor.class);

        Root<Professor> root = criteriaQuery.from(Professor.class);

        criteriaQuery.where(criteriaBuilder.like(root.get("pessoa").get("cpf"), cpf));

        Query query = getEntityManager().createQuery(criteriaQuery);

        Professor professor = null;

        try {
            professor = (Professor) query.getSingleResult();
        } catch (NoResultException exception) {
            LOGGER.log(Level.INFO, exception.getMessage());
        } finally {
            getEntityManager().close();
        }

        return professor;
    }
}
