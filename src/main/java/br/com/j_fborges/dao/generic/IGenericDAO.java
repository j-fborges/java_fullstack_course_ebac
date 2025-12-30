package br.com.j_fborges.dao.generic;

import br.com.j_fborges.domain.Persistent;
import br.com.j_fborges.exception.DAOException;
import br.com.j_fborges.exception.NonUniqueEntryException;
import br.com.j_fborges.exception.TableException;

import java.io.Serializable;
import java.util.Collection;

public interface IGenericDAO <T extends Persistent, E extends Serializable> {

    public T create(T entity) throws DAOException, TypeNotPresentException;

    public void destroy(T entity) throws DAOException;

    public T update(T entity) throws TypeNotPresentException, DAOException;

    public T find(E value) throws NonUniqueEntryException, TableException, DAOException;

    public Collection<T> findAll();
}
