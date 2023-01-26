package br.school.model.dao;

import br.school.model.dao.util.GenericGenericDaoOperations;
import br.school.model.entities.Aluno;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlunoDAO extends GenericGenericDaoOperations<Aluno> {

    private static final Logger LOGGER = Logger.getLogger(AlunoDAO.class.getName());

    public Aluno buscaPessoaPorIdAluno(Integer id) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Aluno> criteriaQuery = criteriaBuilder.createQuery(Aluno.class);
        Root<Aluno> root = criteriaQuery.from(Aluno.class);

        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("pessoa").get("idPessoa"), id));

        Query query = getEntityManager().createQuery(criteriaQuery);

        try {
            return (Aluno) query.getSingleResult();
        } catch (NoResultException exception) {
            LOGGER.log(Level.INFO, exception.getMessage());
            return null;
        } finally {
            getEntityManager().close();
        }
    }
}
