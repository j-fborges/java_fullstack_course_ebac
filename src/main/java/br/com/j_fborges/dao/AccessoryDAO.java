package main.java.br.com.j_fborges.dao;

import main.java.br.com.j_fborges.domain.Accessory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AccessoryDAO implements IAccessoryDAO{

    @Override
    public Accessory register(Accessory accessory) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(accessory);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();


        return accessory;
    }
}
