package br.ufg.inf.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ufg.inf.ctrl.exception.DisciplinaExection;
import br.ufg.inf.model.entities.Disciplina;

public class DisciplinaDAO {

	EntityManager em = DaoFactory.getEntityManager();

	// CREATE
	public Disciplina inserir(Disciplina disciplina) throws DisciplinaExection {

		this.em.getTransaction().begin();
		this.em.persist(disciplina);
		this.em.getTransaction().commit();

		return disciplina;
	}

	// READ
	public List<Disciplina> buscaTodos() throws DisciplinaExection {
		return this.em.createQuery("from Disciplina", Disciplina.class).getResultList();
	}

	public Disciplina buscaPorId(Integer id) throws DisciplinaExection {
		return this.em.find(Disciplina.class, id);
	}

	// UPDATE

	public Disciplina alterar(Disciplina disciplina) throws DisciplinaExection {

		this.em.getTransaction().begin();
		this.em.persist(disciplina);
		this.em.getTransaction().commit();

		return disciplina;
	}

	// DELETE

	public void excluir(Integer id) throws DisciplinaExection {
		this.em.remove(this.buscaPorId(id));
	}
	
	
	public List<Disciplina> buscarPorNome(String str){
		String sql = "from Disciplina d where d.nmDisciplina like :str";
		TypedQuery<Disciplina> query = em.createQuery(sql, Disciplina.class);
		query.setParameter("str", "%"+str+"%");
		return query.getResultList();
	}
	
}
