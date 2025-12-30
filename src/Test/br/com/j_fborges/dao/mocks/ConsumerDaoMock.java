package br.com.j_fborges.dao.mocks;

import br.com.j_fborges.dao.IConsumerDAO;
import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.exception.TypeKeyNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class ConsumerDaoMock implements IConsumerDAO {
    @Override
    public Consumer create(Consumer entity) {
        return entity;
    }

    @Override
    public void destroy(Consumer entity) {


    }

    @Override
    public Consumer update(Consumer entity) {

        return null;
    }

    @Override
    public Consumer find(Long id) {
        Consumer consumer = new Consumer(id.toString(), "Foo", "9985585658", "foo@bar.com","88999999", "Bar", "666", "Bar", "Bar");
        return consumer;
    }

    @Override
    public Collection<Consumer> findAll() {
        return List.of();
    }


}
