package br.com.j_fborges.service;

import br.com.j_fborges.dao.IConsumerDAO;
import br.com.j_fborges.dao.mocks.ConsumerDaoMock;
import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.exception.TypeKeyNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

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
        Consumer expected = consumerService.register(consumer);

        Assert.assertNotNull(expected);
    }

    @Test
    public void delete() {
        consumerService.delete(consumer);
    }

    @Test
    public void updateConsumer() {
        consumer.setName("Manuel");
        consumerService.update(consumer);

        Assert.assertEquals("Manuel", consumer.getName());
    }
}