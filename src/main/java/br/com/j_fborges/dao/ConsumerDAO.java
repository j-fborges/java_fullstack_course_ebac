package br.com.j_fborges.dao;

import br.com.j_fborges.dao.generic.GenericDAO;
import br.com.j_fborges.domain.Consumer;

public class ConsumerDAO extends GenericDAO<Consumer, Long> implements IConsumerDAO {

    public ConsumerDAO(){
        super(Consumer.class);
    }

    @Override
    public Class<Consumer> getClassType() {
        return Consumer.class;
    }
}
