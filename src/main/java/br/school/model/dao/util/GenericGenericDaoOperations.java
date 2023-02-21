package br.school.model.dao.util;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class GenericGenericDaoOperations<T extends Serializable> implements GenericDaoOperationsInterface<T> {

    private Class<T> tClass;

    private DaoFactory daoFactory;

    public GenericGenericDaoOperations() {
        this.tClass = (Class<T>)
                ((ParameterizedType) getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
        this.daoFactory = new DaoFactory();
    }

    public EntityManager getEntityManager() {
        return this.daoFactory.getEntityManager();
    }

    @Override
    public T inserir(T entidade) {
        getEntityManager().getTransaction().begin();

        if (getEntityManager().contains(entidade)) {
            getEntityManager().persist(entidade);
        } else {
            entidade = getEntityManager().merge(entidade);
        }

        getEntityManager().getTransaction().commit();

        daoFactory.closeConnection();

        return entidade;
    }

    @Override
    public T atualizar(T entidade) {
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(entidade);
        getEntityManager().getTransaction().commit();

        daoFactory.closeConnection();

        return entidade;
    }

    @Override
    public List<T> buscarTodos() {
        List<T> entidades = getEntityManager().createQuery("from " + tClass.getName()).getResultList();

        daoFactory.closeConnection();

        return entidades;
    }

    @Override
    public T buscarId(Integer id) {
        T entidade = getEntityManager().find(tClass, id);

        daoFactory.closeConnection();

        return entidade;
    }


    @Override
    public void excluir(T entidade) {
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(getEntityManager().contains(entidade) ? entidade : getEntityManager().merge(entidade));
        getEntityManager().getTransaction().commit();

        daoFactory.closeConnection();
    }
}
