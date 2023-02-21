package br.school.model.dao;

import br.school.model.dao.util.GenericGenericDaoOperations;
import br.school.model.entities.Disciplina;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DisciplinaDAO extends GenericGenericDaoOperations<Disciplina> {

    public List<Disciplina> buscarPorNomeLike(String name) {
        return buscarPorNome("%" + name + "%");
    }

    public List<Disciplina> buscarPorNomeEquals(String name) {
        return buscarPorNome(name);
    }

    private List<Disciplina> buscarPorNome(String param) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Disciplina> criteriaQuery = criteriaBuilder.createQuery(Disciplina.class);

        Root<Disciplina> disciplinaRoot = criteriaQuery.from(Disciplina.class);
        criteriaQuery.select(disciplinaRoot)
                .where(criteriaBuilder.like(disciplinaRoot.get("nomeDisciplina"), param));

        Query query = getEntityManager().createQuery(criteriaQuery);

        List<Disciplina> disciplinas = query.getResultList();

        getEntityManager().close();

        return disciplinas;
    }
}
