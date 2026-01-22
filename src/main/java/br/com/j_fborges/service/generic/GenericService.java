package br.com.j_fborges.service.generic;

import br.com.j_fborges.dao.generic.IGenericDAO;
import br.com.j_fborges.domain.Persistent;
import br.com.j_fborges.exception.DAOException;
import br.com.j_fborges.exception.NonUniqueEntryException;
import br.com.j_fborges.exception.TableException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;

public class GenericService<T extends Persistent, E extends Serializable> implements IGenericService<T, E> {

    protected IGenericDAO<T,E> dao;

    public GenericService(IGenericDAO<T,E> dao) {
        this.dao = dao;
    }

    @Override
    public T register(T entity) throws SQLException {
        return this.dao.create(entity);
    }

    @Override
    public void delete(T entity) {
        this.dao.destroy(entity);
    }

    @Override
    public T update(T entity) {
        return this.dao.update(entity);
    }

    @Override
    public T find(Long id) throws DAOException {
            try {
                return this.dao.find((E) id);
            } catch (NonUniqueEntryException | TableException e) {
                e.printStackTrace();
            }
            return null;
        }

    @Override
    public Collection findAll() {

        return this.dao.findAll();
    }
}
