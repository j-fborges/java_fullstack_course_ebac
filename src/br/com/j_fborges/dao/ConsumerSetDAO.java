package br.com.j_fborges.dao;

import br.com.j_fborges.domain.Consumer;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ConsumerSetDAO implements IConsumerDAO{

    private Set<Consumer> set;

    public ConsumerSetDAO() {
        this.set = new HashSet<>();
    }

    public Boolean create(Consumer consumer) {
        return this.set.add(consumer);
    }

    public void destroy(Long cpf) {
        Consumer consumerEncontrato = null;
        for (Consumer consumer : this.set) {
            if (consumer.getIdNumber().equals(cpf)) {
                consumerEncontrato = consumer;
                break;
            }
        }

        if (consumerEncontrato != null) {
            this.set.remove(consumerEncontrato);
        }
    }

    @Override
    public void update(Consumer consumer) {
        if (this.set.contains(consumer)) {
            for (Consumer consumerRegistered : this.set) {
                if (consumerRegistered.equals(consumer)) {
                    consumerRegistered.setName(consumer.getName());
                    consumerRegistered.setTel(consumer.getTel());
                    consumerRegistered.setAddressNumber(consumer.getAddressNumber());
                    consumerRegistered.setAddress(consumer.getAddress());
                    consumerRegistered.setCity(consumer.getCity());
                    consumerRegistered.setState(consumer.getState());
                    break;
                }
            }
        }
    }

    @Override
    public Consumer find(Long cpf) {
        for (Consumer consumerRegistered : this.set) {
            if (consumerRegistered.getIdNumber().equals(cpf)) {
                return consumerRegistered;
            }
        }
        return null;
    }

    @Override
    public Collection<Consumer> findAll() {
        return this.set;
    }
}
