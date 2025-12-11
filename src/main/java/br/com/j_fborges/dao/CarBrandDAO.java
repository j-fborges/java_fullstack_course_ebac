package main.java.br.com.j_fborges.dao;

import main.java.br.com.j_fborges.domain.CarBrand;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CarBrandDAO implements ICarBrandDAO{


    @Override
    public CarBrand register(CarBrand carBrand) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(carBrand);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();


        return carBrand;
    }
}
