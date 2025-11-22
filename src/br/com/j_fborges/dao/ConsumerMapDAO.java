package br.com.j_fborges.dao;

import br.com.j_fborges.dao.generic.GenericDAO;
import br.com.j_fborges.domain.Consumer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ConsumerMapDAO extends GenericDAO<Consumer, Long> implements IConsumerDAO {

    public ConsumerMapDAO(){
        super();
    }

    @Override
    public Class<Consumer> getClassType() {
        return Consumer.class;
    }

    @Override
    public void updateData(Consumer consumer, Consumer entityRegistered) {
        entityRegistered.setName(consumer.getName());
        entityRegistered.setTel(consumer.getTel());
        entityRegistered.setAddressNumber(consumer.getAddressNumber());
        entityRegistered.setAddress(consumer.getAddress());
        entityRegistered.setCity(consumer.getCity());
        entityRegistered.setState(consumer.getState());
    }
}
