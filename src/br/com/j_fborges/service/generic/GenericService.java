package br.com.j_fborges.service.generic;

import br.com.j_fborges.dao.generic.IGenericDAO;
import br.com.j_fborges.domain.Persistent;
import br.com.j_fborges.exception.TypeKeyNotFoundException;

import java.io.Serializable;
import java.util.Collection;

public class GenericService<T extends Persistent, E extends Serializable> implements IGenericService<T, E> {

    protected IGenericDAO<T,E> dao;

    public GenericService(IGenericDAO<T,E> dao) {
        this.dao = dao;
    }

    @Override
    public Boolean register(T entity) throws TypeKeyNotFoundException {
        return this.dao.create(entity);
    }

    @Override
    public void delete(E value) {
        this.dao.destroy(value);
    }

    @Override
    public void update(T entity) throws TypeKeyNotFoundException {
        this.dao.update(entity);
    }

    @Override
    public T find(E value) {
        return this.dao.find(value);
    }

    @Override
    public Collection findAll() {
        return this.dao.findAll();
    }
}
