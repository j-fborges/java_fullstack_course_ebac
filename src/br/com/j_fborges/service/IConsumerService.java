package br.com.j_fborges.service;

import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.service.generic.IGenericService;

import java.sql.SQLException;
import java.util.Collection;

public interface IConsumerService extends IGenericService<Consumer, Long> {

    Boolean register(Consumer consumer) throws SQLException;

    void delete(Long id);

    void update(Consumer consumer);

    public Consumer find(Consumer consumer);

    public Collection<Consumer> loadConsumers();
}
