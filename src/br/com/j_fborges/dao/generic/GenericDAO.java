package br.com.j_fborges.dao.generic;

import br.com.j_fborges.annotation.TypeIDKey;
import br.com.j_fborges.dao.SingletonGenericDAOMap;
import br.com.j_fborges.domain.Persistent;
import br.com.j_fborges.exception.TypeKeyNotFoundException;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class GenericDAO<T extends Persistent, E extends Serializable> implements IGenericDAO<T, E>{

    private SingletonGenericDAOMap singletonMap;

    public abstract Class<T> getClassType();

    public abstract void updateData(T entity, T entityRegistered);

    public GenericDAO() {
        this.singletonMap = SingletonGenericDAOMap.getInstance();
        this.singletonMap.printMap();
    }

    public E getIDKey(T entity) throws TypeKeyNotFoundException {
        Field[] fields = entity.getClass().getDeclaredFields();
        E returnValue = null;
        for (Field field : fields) {
            if (field.isAnnotationPresent(TypeIDKey.class)) {
                TypeIDKey typeIDKey = field.getAnnotation(TypeIDKey.class);
                String methodName = typeIDKey.value();
                try {
                    Method method = entity.getClass().getMethod(methodName);
                    returnValue = (E) method.invoke(entity);
                    return returnValue;
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
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

//        Map<E, T> innerMap = (Map<E, T>) this.singletonMap.getMap().get(getClassType());
        Map<E, T> innerMap = getMap();
        E idKey = getIDKey(entity);
        if (innerMap.containsKey(idKey)) {
            return false;
        }
        innerMap.put(idKey, entity);
        return true;
    }

    private Map<E, T> getMap() {
        Map<E, T> innerMap = (Map<E, T>) this.singletonMap.getMap().get(getClassType());
        if (innerMap == null) {
            innerMap = new HashMap<>();
            this.singletonMap.getMap().put(getClassType(), innerMap);
        }

        SingletonGenericDAOMap.printMap();
        return innerMap;
    }

    @Override
    public void destroy(E value) {
        Map<E, T> innerMap = (Map<E, T>) this.singletonMap.getMap().get(getClassType());
        T objectRegistered = innerMap.get(value);
        if (objectRegistered != null) {
            innerMap.remove(value, objectRegistered);
        }
    }

    @Override
    public void update(T entity) throws TypeKeyNotFoundException {
        Map<E, T> innerMap = (Map<E, T>) this.singletonMap.getMap().get(getClassType());
        E idKey = getIDKey(entity);
        T objectRegistered = innerMap.get(idKey);
        if (objectRegistered != null) {
            updateData(entity, objectRegistered);
        }
    }

    @Override
    public T find(E value) {
        Map<E, T> innerMap = (Map<E, T>) this.singletonMap.getMap().get(getClassType());
        return innerMap.get(value);
    }

    @Override
    public Collection<T> findAll() {
        Map<Long, T> innerMap = (Map<Long, T>) this.singletonMap.getMap().get(getClassType());
        return innerMap.values();
    }
}
