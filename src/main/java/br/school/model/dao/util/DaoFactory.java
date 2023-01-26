package br.school.model.dao.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

public class DaoFactory {

    private EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            Properties props = new Properties();
            props.setProperty("hibernate.connection.autocommit", "true");
            entityManagerFactory = Persistence.createEntityManagerFactory("school-management", props);
            entityManager = entityManagerFactory.createEntityManager();
        }

        return entityManager;
    }

    public void closeConnection() {
        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
