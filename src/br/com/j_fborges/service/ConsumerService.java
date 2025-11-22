package br.com.j_fborges.service;

import br.com.j_fborges.dao.IConsumerDAO;
import br.com.j_fborges.dao.generic.IGenericDAO;
import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.service.generic.GenericService;
import br.com.j_fborges.service.generic.IGenericService;

public class ConsumerService extends GenericService<Consumer, Long> implements IConsumerService{

    public ConsumerService(IConsumerDAO dao) {
        super(dao);
    }

    @Override
    public Consumer findByIdNumber(Long idNumber) {
        return this.dao.find(idNumber);
    }
}
