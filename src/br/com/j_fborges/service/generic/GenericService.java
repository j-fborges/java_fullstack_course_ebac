package br.com.j_fborges.service.generic;

import br.com.j_fborges.dao.generic.IGenericDAO;
import br.com.j_fborges.domain.Persistent;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;

public class GenericService<T extends Persistent, E extends Serializable> implements IGenericService<T, E> {

    protected IGenericDAO<T,E> dao;

    public GenericService(IGenericDAO<T,E> dao) {
        this.dao = dao;
    }

    @Override
    public Boolean register(T entity) throws SQLException {
        return this.dao.create(entity);
    }

    @Override
    public void delete(Long id) {
        this.dao.destroy(id);
    }

    @Override
    public void update(T entity) {
        this.dao.update(entity);
    }

    @Override
    public T find(Long id) {
        return this.dao.find(id);
    }

    @Override
    public Collection findAll() {

        return this.dao.findAll();
    }

    @Override
    public Long loadCurrSequenceIdKey(){
        return dao.getCurrSequenceIdKey();
    }
}
