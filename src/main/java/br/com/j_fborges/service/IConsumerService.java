package br.com.j_fborges.service;

import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.service.generic.IGenericService;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public interface IConsumerService extends IGenericService<Consumer, Long> {

    Consumer register(Consumer consumer) throws SQLException;

    void delete(Consumer consumer);

    Consumer update(Consumer consumer);

    Collection<Consumer> loadConsumers();
    
    List<Consumer> filterConsumers(String query);
}
