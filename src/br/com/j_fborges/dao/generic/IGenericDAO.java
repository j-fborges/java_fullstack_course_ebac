package br.com.j_fborges.dao.generic;

import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.domain.Persistent;

import java.util.Collection;

public interface IGenericDAO <T extends Persistent> {

    public Boolean create(T entity);

    public void destroy(Long cpf);

    public void update(T entity);

    public T find(Long cpf);

    public Collection<T> findAll();
}
