package main.java.br.com.j_fborges.dao;

import main.java.br.com.j_fborges.domain.CarModel;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CarModelDAO implements ICarModelDAO{

    @Override
    public CarModel register(CarModel carModel) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(carModel);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();


        return carModel;
    }
}
