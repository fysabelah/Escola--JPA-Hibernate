package br.ufg.inf.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.ufg.inf.ctrl.exception.MatriculaException;
import br.ufg.inf.model.entities.Matricula;

public class MatriculaDAO {
	EntityManager em = DaoFactory.getEntityManager();
	
	//CREATE
	public Matricula inserir(Matricula matricula) throws MatriculaException{
		this.em.getTransaction().begin();
		this.em.persist(matricula);
		this.em.getTransaction().commit();
		
		return(matricula);
	}
	
	//READ
	public List<Matricula> buscaTodos() throws MatriculaException{
		return(this.em.createQuery("from Matricula", Matricula.class).getResultList());
	}
	
	public Matricula buscaPorId(Integer id) throws MatriculaException{
		return(this.em.find(Matricula.class, id));
	}
	
	
	//UPDATE
	public Matricula alterar(Matricula matricula) throws MatriculaException{
		this.em.getTransaction().begin();
		this.em.persist(matricula);
		this.em.getTransaction().commit();
		
		return(matricula);
	}
	
	//DELETE
	public void excluir(Integer id) throws MatriculaException{
		this.em.remove(this.buscaPorId(id));
	}
}
