package br.com.j_fborges.service;

import br.com.j_fborges.dao.IConsumerDAO;
import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.service.generic.GenericService;

import java.util.Collection;

public class ConsumerService extends GenericService<Consumer, Long> implements IConsumerService {

    public ConsumerService(IConsumerDAO dao) {
        super(dao);
    }

    @Override
    public Collection<Consumer> loadConsumers() {
        return dao.findAll();
    }
}
