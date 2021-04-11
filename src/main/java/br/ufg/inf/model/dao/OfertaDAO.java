package br.ufg.inf.model.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.ufg.inf.ctrl.exception.OfertaExection;
import br.ufg.inf.model.entities.Oferta;

public class OfertaDAO {

	EntityManager em = DaoFactory.getEntityManager();

	public Oferta inserir(Oferta oferta) throws OfertaExection {
		this.em.getTransaction().begin();
		this.em.persist(oferta);
		this.em.getTransaction().commit();
		return oferta;
	}

	public List<Oferta> buscaTodos() throws OfertaExection {
		return this.em.createQuery("from Oferta", Oferta.class).getResultList();
	}

	public Oferta buscaPorId(Integer id) throws OfertaExection {
		return this.em.find(Oferta.class, id);
	}

	public Oferta alterar(Oferta oferta) throws OfertaExection {
		this.em.getTransaction().begin();
		this.em.persist(oferta);
		this.em.getTransaction().commit();
		return oferta;
	}

	public void excluir(Integer id) throws OfertaExection {
		this.em.remove(this.buscaPorId(id));
	}

}
