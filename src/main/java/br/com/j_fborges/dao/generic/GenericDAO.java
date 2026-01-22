package br.com.j_fborges.dao.generic;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.j_fborges.domain.Persistent;
import br.com.j_fborges.exception.DAOException;
import br.com.j_fborges.exception.NonUniqueEntryException;
import br.com.j_fborges.exception.TableException;

public abstract class GenericDAO<T extends Persistent, E extends Serializable> implements IGenericDAO<T, E> {

    protected Class<T> persistentClass;
    
    @PersistenceContext
    protected EntityManager entityManager;

    public GenericDAO(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public abstract Class<T> getClassType();

    @Override
    public T create(T entity) throws DAOException {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void destroy(T entity) throws DAOException {
        entity = entityManager.merge(entity);
        entityManager.remove(entity);
    }

    @Override
    public T update(T entity) throws DAOException {
        entity = entityManager.merge(entity);
        return entity;
    }

    @Override
    public T find(E value) throws NonUniqueEntryException, DAOException, TableException {
        T entity = entityManager.find(this.persistentClass, value);
        return entity;
    }

    @Override
    public Collection<T> findAll() throws DAOException {
        List<T> list = entityManager.createQuery(getSqlSelectAll(), this.persistentClass).getResultList();
        return list;
    }

    protected String getSqlSelectAll(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT obj FROM ");
        sb.append(this.persistentClass.getSimpleName());
        sb.append(" obj");
        return sb.toString();
    };
}
