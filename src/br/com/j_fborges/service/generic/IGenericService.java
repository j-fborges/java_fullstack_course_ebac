package br.com.j_fborges.service.generic;

import br.com.j_fborges.domain.Persistent;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;

public interface IGenericService <T extends Persistent, E extends Serializable> {

    public Boolean register(T entity) throws SQLException;

    public void delete(Long id);

    public void update(T entity);

    public T find(Long id);

    public Collection<T> findAll();

    Long loadCurrSequenceIdKey();

}
