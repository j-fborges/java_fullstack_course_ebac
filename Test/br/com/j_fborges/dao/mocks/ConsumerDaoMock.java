package br.com.j_fborges.dao.mocks;

import br.com.j_fborges.dao.IConsumerDAO;
import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.exception.TypeKeyNotFoundException;

import java.util.Collection;
import java.util.List;

public class ConsumerDaoMock implements IConsumerDAO {
    @Override
    public Boolean create(Consumer entity) throws TypeKeyNotFoundException {
        return true;
    }

    @Override
    public void destroy(Long value) {

    }

    @Override
    public void update(Consumer entity) throws TypeKeyNotFoundException {

    }

    @Override
    public Consumer find(Long value) {
        Consumer consumer = new Consumer("Foo", value.toString(),"88999999", "Bar", "666", "Bar", "Bar");
        return consumer;
    }

    @Override
    public Collection<Consumer> findAll() {
        return List.of();
    }
}
