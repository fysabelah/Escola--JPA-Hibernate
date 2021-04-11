package br.ufg.inf.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoFactory {

	static EntityManagerFactory emf;
	static EntityManager em;
	
	static EntityManager getEntityManager() {
		if(em == null) {
			emf = Persistence.createEntityManagerFactory("aula-jpa");
			em = emf.createEntityManager();
		}
		return em;
	}
	
	static void closeConnection() {
		if(em.isOpen()) {
			em.close();
		}
	}
	
}
