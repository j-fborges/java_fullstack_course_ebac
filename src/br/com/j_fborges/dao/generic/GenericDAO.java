package br.com.j_fborges.dao.generic;

import br.com.j_fborges.dao.SingletonGenericDAOMap;
import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.domain.Persistent;

import java.util.Collection;
import java.util.Map;

public abstract class GenericDAO<T extends Persistent> implements IGenericDAO<T>{

    private SingletonGenericDAOMap singletonMap;

    public abstract Class<T> getClassType();

    public abstract void updateData(T entity, T entityCadastrado);

    public GenericDAO() {
        this.singletonMap = SingletonGenericDAOMap.getInstance();
        this.singletonMap.printMap();
    }

    @Override
    public Boolean create(T entity) {

        Map<Long, T> innerMap = (Map<Long, T>) this.singletonMap.getMap().get(getClassType());
        if (innerMap.containsKey(entity.getIdCode())) {
            return false;
        }
        innerMap.put(entity.getIdCode(), entity);
        return true;
    }

    @Override
    public void destroy(Long value) {
//        Consumer consumerRegistered = map.get(cpf);
//        map.remove(consumerRegistered.getIdNumber(), consumerRegistered);
        Map<Long, T> innerMap = (Map<Long, T>) this.singletonMap.getMap().get(getClassType());
        T objectRegistered = innerMap.get(value);
        if (objectRegistered != null) {
            innerMap.remove(value, objectRegistered);
        }
    }

    @Override
    public void update(T entity) {
//        Consumer consumerRegistered = map.get(consumer.getIdNumber());
//        consumerRegistered.setName(consumer.getName());
//        consumerRegistered.setTel(consumer.getTel());
//        consumerRegistered.setAddressNumber(consumer.getAddressNumber());
//        consumerRegistered.setAddress(consumer.getAddress());
//        consumerRegistered.setCity(consumer.getCity());
//        consumerRegistered.setState(consumer.getState());
        Map<Long, T> innerMap = (Map<Long, T>) this.singletonMap.getMap().get(getClassType());
        T objectRegistered = innerMap.get(entity.getIdCode());
        if (objectRegistered != null) {
            updateData(entity, objectRegistered);
        }
    }

    @Override
    public T find(Long value) {
//        return this.map.get(cpf);
        Map<Long, T> innerMap = (Map<Long, T>) this.singletonMap.getMap().get(getClassType());
        return innerMap.get(value);
    }

    @Override
    public Collection<T> findAll() {
        Map<Long, T> innerMap = (Map<Long, T>) this.singletonMap.getMap().get(getClassType());
        return innerMap.values();
    }
}
