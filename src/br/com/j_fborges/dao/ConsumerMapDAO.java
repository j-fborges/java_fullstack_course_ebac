package br.com.j_fborges.dao;

import br.com.j_fborges.domain.Consumer;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class ConsumerMapDAO implements IConsumerDAO {

    private Map<Long, Consumer> map;

    public ConsumerMapDAO() {
        map = new TreeMap<>();
    }

    @Override
    public Boolean create(Consumer consumer) {
        if (map.containsKey(consumer.getIdNumber())) {
            return false;
        }

        map.put(consumer.getIdNumber(), consumer);
        return true;
    }

    @Override
    public void destroy(Long cpf) {
        Consumer consumerRegistered = map.get(cpf);
        map.remove(consumerRegistered.getIdNumber(), consumerRegistered);
    }

    @Override
    public void update(Consumer consumer) {
        Consumer consumerRegistered = map.get(consumer.getIdNumber());
        consumerRegistered.setName(consumer.getName());
        consumerRegistered.setTel(consumer.getTel());
        consumerRegistered.setAddressNumber(consumer.getAddressNumber());
        consumerRegistered.setAddress(consumer.getAddress());
        consumerRegistered.setCity(consumer.getCity());
        consumerRegistered.setState(consumer.getState());
    }

    @Override
    public Consumer find(Long cpf) {
        return this.map.get(cpf);
    }

    @Override
    public Collection<Consumer> findAll() {
        return this.map.values();
    }
}
