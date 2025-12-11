package main.java.br.com.j_fborges.dao;

import main.java.br.com.j_fborges.domain.Car;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CarDAO implements ICarDAO{
    @Override
    public Car register(Car car) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(car);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();


        return car;

    }
}
