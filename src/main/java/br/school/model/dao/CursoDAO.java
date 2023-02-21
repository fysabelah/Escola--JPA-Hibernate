package br.school.model.dao;

import br.school.model.dao.util.GenericGenericDaoOperations;
import br.school.model.entities.Curso;

import javax.persistence.TypedQuery;
import java.util.List;

public class CursoDAO extends GenericGenericDaoOperations<Curso> {

    public List<Curso> buscarPorNome(String nome) {
        return findByName("%" + nome + "%");
    }

    public List<Curso> buscarPorNomeIdentico(String nome) {
        return findByName(nome);
    }

    private List<Curso> findByName(String param) {
        String sql = "from Curso d where UPPER(d.nomeCurso) like UPPER(:name)";

        TypedQuery<Curso> query = getEntityManager().createQuery(sql, Curso.class);

        query.setParameter("name", param);

        List<Curso> cursos = query.getResultList();

        getEntityManager().close();

        return cursos;
    }
}
