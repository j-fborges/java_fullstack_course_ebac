package br.com.j_fborges.dao.generic;

import br.com.j_fborges.domain.Persistent;
import br.com.j_fborges.exception.DAOException;
import br.com.j_fborges.exception.NonUniqueEntryException;
import br.com.j_fborges.exception.TableException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public abstract class GenericDAO<T extends Persistent, E extends Serializable> implements IGenericDAO<T, E> {

    protected EntityManagerFactory entityManagerFactory;

    protected EntityManager entityManager;

    private Class<T> persistentClass;

    public GenericDAO(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public abstract Class<T> getClassType();

    @Override
    public T create(T entity) throws DAOException {
        openConnection();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        closeConnection();
        return entity;
    }

    @Override
    public void destroy(T entity) throws DAOException {
        openConnection();
        entity = entityManager.merge(entity);
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
        closeConnection();
    }

    @Override
    public T update(T entity) throws DAOException {
        openConnection();
        entity = entityManager.merge(entity);
        entityManager.getTransaction().commit();
        closeConnection();
        return entity;
    }

    @Override
    public T find(E value) throws NonUniqueEntryException, DAOException, TableException {
        openConnection();
        T entity = entityManager.find(this.persistentClass, value);
        entityManager.getTransaction().commit();
        closeConnection();
        return entity;
    }

    @Override
    public Collection<T> findAll() throws DAOException {
        openConnection();
        List<T> list = entityManager.createQuery(getSqlSelectAll(), this.persistentClass).getResultList();
        closeConnection();
        return list;
    }

    protected String getSqlSelectAll(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT obj FROM ");
        sb.append(this.persistentClass.getSimpleName());
        sb.append(" obj");
        return sb.toString();
    };

    protected void openConnection() {
        entityManagerFactory =
                Persistence.createEntityManagerFactory("ExemploJPA");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }


    protected void closeConnection() {
        entityManager.close();
        entityManagerFactory.close();
    }
}
