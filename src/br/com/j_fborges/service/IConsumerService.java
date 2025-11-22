package br.com.j_fborges.service;

import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.exception.TypeKeyNotFoundException;
import br.com.j_fborges.service.generic.IGenericService;

public interface IConsumerService extends IGenericService<Consumer, Long> {

    Boolean register(Consumer consumer) throws TypeKeyNotFoundException;

    Consumer findByIdNumber(Long idNumber);

    void delete(Long idNumber);

    void update(Consumer consumer) throws TypeKeyNotFoundException;
}
