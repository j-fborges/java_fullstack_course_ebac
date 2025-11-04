package br.com.j_fborges.dao;

import br.com.j_fborges.domain.Consumer;

import java.util.Collection;

public interface IConsumerDAO {

    public Boolean create(Consumer consumer);

    public void destroy(Long cpf);

    public void update(Consumer consumer);

    public Consumer find(Long cpf);

    public Collection<Consumer> findAll();
}
