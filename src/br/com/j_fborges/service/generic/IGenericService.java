package br.com.j_fborges.service.generic;

import br.com.j_fborges.domain.Persistent;
import br.com.j_fborges.exception.TypeKeyNotFoundException;

import java.io.Serializable;
import java.util.Collection;

public interface IGenericService <T extends Persistent, E extends Serializable> {

    public Boolean register(T entity) throws TypeKeyNotFoundException;

    public void delete(E value);

    public void update(T entity) throws TypeKeyNotFoundException;

    public T find(E value);

    public Collection<T> findAll();
}
