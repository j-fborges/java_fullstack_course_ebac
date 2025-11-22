package br.com.j_fborges.dao.generic;

import br.com.j_fborges.annotation.TypeIDKey;
import br.com.j_fborges.dao.SingletonGenericDAOMap;
import br.com.j_fborges.domain.Persistent;
import br.com.j_fborges.exception.TypeKeyNotFoundException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

    public Long getIDKey(T entity) throws TypeKeyNotFoundException {
        Field[] fields = entity.getClass().getDeclaredFields();
        Long returnValue = null;
        for (Field field : fields) {
            if (field.isAnnotationPresent(TypeIDKey.class)) {
                TypeIDKey typeIDKey = field.getAnnotation(TypeIDKey.class);
                String methodName = typeIDKey.value();
                try {
                    Method method = entity.getClass().getMethod(methodName);
                    returnValue = (Long) method.invoke(entity);
                    return returnValue;
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    //Criar exception de negócio TypeKeyNotFoundException
                    e.printStackTrace();
                    throw new TypeKeyNotFoundException("Object ID key " + entity.getClass() + " not found", e);
                }
            }
        }
        if (returnValue == null) {
            String msg = "Object ID key " + entity.getClass() + " not found";
            System.out.println("**** ERROR ****" + msg);
            throw new TypeKeyNotFoundException(msg);
        }
        return null;
    }

    @Override
    public Boolean create(T entity) throws TypeKeyNotFoundException {

        Map<Long, T> innerMap = (Map<Long, T>) this.singletonMap.getMap().get(getClassType());
        if (innerMap.containsKey(getIDKey(entity))) {
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
