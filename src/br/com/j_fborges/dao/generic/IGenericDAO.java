package br.com.j_fborges.dao.generic;

import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.domain.Persistent;
import br.com.j_fborges.exception.TypeKeyNotFoundException;

import java.io.Serializable;
import java.util.Collection;

public interface IGenericDAO <T extends Persistent, E extends Serializable> {

    public Boolean create(T entity) throws TypeKeyNotFoundException;

    public void destroy(E value);

    public void update(T entity) throws TypeKeyNotFoundException;

    public T find(E value);

    public Collection<T> findAll();
}
