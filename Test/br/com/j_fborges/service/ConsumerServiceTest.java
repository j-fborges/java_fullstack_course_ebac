package br.com.j_fborges.service;

import br.com.j_fborges.dao.IConsumerDAO;
import br.com.j_fborges.dao.mocks.ConsumerDaoMock;
import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.exception.TypeKeyNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class ConsumerServiceTest {

    private IConsumerService consumerService;

    private Consumer consumer;

    public ConsumerServiceTest() {
        IConsumerDAO dao = new ConsumerDaoMock();
        consumerService = new ConsumerService(dao);
    }

    @Before
    public void init() {
        consumer = new Consumer(
                "99",
                "Rodrigo",
                "123123484",
                "ro@ro.com0",
                "1199999999",
                "End",
                "10",
                "São Paulo",
                "SP"
        );

    }

    @Test
    public void find() {
        Consumer expectedResult = consumerService.find(consumer.getId());
        Assert.assertNotNull(expectedResult);
    }

    @Test
    public void register() throws SQLException {
        Boolean expected = consumerService.register(consumer);

        Assert.assertTrue(expected);
    }

    @Test
    public void delete() {
        consumerService.delete(consumer.getIdNumber());
    }

    @Test
    public void updateConsumer() {
        consumer.setName("Manuel");
        consumerService.update(consumer);

        Assert.assertEquals("Manuel", consumer.getName());
    }
}