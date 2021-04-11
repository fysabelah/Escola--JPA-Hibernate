package br.ufg.inf.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ufg.inf.ctrl.exception.PessoaExection;
import br.ufg.inf.model.entities.Pessoa;

public class PessoaDAO {

	EntityManager em = DaoFactory.getEntityManager();

	public Pessoa inserir(Pessoa pessoa) throws PessoaExection {

		this.em.getTransaction().begin();
		this.em.persist(pessoa);
		this.em.getTransaction().commit();

		return pessoa;
	}

	public List<Pessoa> buscaTodos() throws PessoaExection {
		return this.em.createQuery("from Pessoa", Pessoa.class).getResultList();
	}

	public Pessoa buscaPorId(Integer id) throws PessoaExection {
		return this.em.find(Pessoa.class, id);
	}

	public Pessoa alterar(Pessoa pessoa) throws PessoaExection {

		this.em.getTransaction().begin();
		this.em.persist(pessoa);
		this.em.getTransaction().commit();

		return pessoa;
	}

	public void excluir(Integer id) throws PessoaExection {
		this.em.remove(this.buscaPorId(id));
	}
	
	public List<Pessoa> buscarPorNome(String str){
		String sql = "from Pessoa d where d.nmPessoa like :str";
		TypedQuery<Pessoa> query = em.createQuery(sql, Pessoa.class);
		query.setParameter("str", "%"+str+"%");
		return query.getResultList();
	}
}
