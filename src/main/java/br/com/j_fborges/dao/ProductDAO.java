package main.java.br.com.j_fborges.dao;

import main.java.br.com.j_fborges.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;


public class ProductDAO implements IProductDAO {


    @Override
    public Product register(Product product) {

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();


        return product;
    }

    @Override
    public void delete(Product product) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        product = entityManager.merge(product);
        entityManager.remove(product);
        entityManager.getTransaction().commit();

        entityManager.close();
        entityManagerFactory.close();

    }

    @Override
    public List<Product> findAll() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root);

        TypedQuery<Product> tpQuery =
                entityManager.createQuery(query);
        List<Product> list = tpQuery.getResultList();

        entityManager.close();
        entityManagerFactory.close();
        return list;
    }

    @Override
    public Product find(Long productId) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT p FROM Product p ");
        sb.append("WHERE p.id = :productId");

        entityManager.getTransaction().begin();
        TypedQuery<Product> query =
                entityManager.createQuery(sb.toString(), Product.class);
        query.setParameter("productId", productId);
        Product product = query.getSingleResult();

        entityManager.close();
        entityManagerFactory.close();

        return product;
    }

    @Override
    public Product findByProductCode(String productIdCode) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();


        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);
        query.select(root).where(builder.equal(root.get("idCode"), productIdCode));

        TypedQuery<Product> tpQuery =
                entityManager.createQuery(query);
        Product product = tpQuery.getSingleResult();

        entityManager.close();
        entityManagerFactory.close();

        return product;
    }
}
