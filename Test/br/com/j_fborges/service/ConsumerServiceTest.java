package br.com.j_fborges.service;

import br.com.j_fborges.dao.IConsumerDAO;
import br.com.j_fborges.dao.mocks.ConsumerDaoMock;
import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.exception.TypeKeyNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
                "Rodrigo",
                "123123484",
                "1199999999",
                "End",
                "10",
                "São Paulo",
                "SP"
        );

    }

    @Test
    public void findByIdNumber() {
        Consumer expectedResult = consumerService.findByIdNumber(consumer.getIdNumber());
        Assert.assertNotNull(expectedResult);
    }

    @Test
    public void register() throws TypeKeyNotFoundException {
        Boolean expected = consumerService.register(consumer);

        Assert.assertTrue(expected);
    }

    @Test
    public void delete() {
        consumerService.delete(consumer.getIdNumber());
    }

    @Test
    public void alterarConsumer() throws TypeKeyNotFoundException {
        consumer.setName("Manuel");
        consumerService.update(consumer);

        Assert.assertEquals("Manuel", consumer.getName());
    }
}