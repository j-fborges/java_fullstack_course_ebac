package br.com.j_fborges.dao;

import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.exception.TypeKeyNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ConsumerMapDAOTest {

    private final IConsumerDAO consumerDao;
    private Consumer consumer;

    public ConsumerMapDAOTest() {
        this.consumerDao = new ConsumerMapDAO();
    }

    @Before
    public void init() throws TypeKeyNotFoundException {
        consumer = new Consumer(
                "Rodrigo",
                "123123484",
                "1199999999",
                "End",
                "10",
                "São Paulo",
                "SP"
        );
        consumerDao.create(consumer);
    }

    @Test
    public void find() {
        Consumer expectedResult = consumerDao.find(consumer.getIdCode());
        Assert.assertNotNull(expectedResult);
    }

    @Test
    public void create() throws TypeKeyNotFoundException {
        consumer.setIdNumber(26846513543L);
        Boolean expectedResult = consumerDao.create(consumer);
        Assert.assertTrue(expectedResult);
    }

    @Test
    public void destroy() {
        consumerDao.destroy(consumer.getIdCode());
        Assert.assertNull(consumerDao.find(consumer.getIdNumber()));
    }

    @Test
    public void update() throws TypeKeyNotFoundException {
        Consumer expectedResult = new Consumer(
                "Manuel",
                "123123484",
                "684643234384",
                "Rua",
                "60",
                "Rio de Janeiro",
                "RJ"
        );
        assertEquals("Rodrigo", consumerDao.find(123123484L).getName());
        consumerDao.update(expectedResult);
        assertEquals("Manuel", consumerDao.find(123123484L).getName());
    }

    @org.junit.Test
    public void getClassType() {
        assertEquals("ConsumerMapDAO", consumerDao.getClass().getSimpleName());
    }

    @Test
    public void findAll() throws TypeKeyNotFoundException {

        Integer beforeValue = consumerDao.findAll().toArray().length;

        consumerDao.create(new Consumer(
                "Manuel",
                "6869646948",
                "684643234384",
                "Rua",
                "60",
                "Rio de Janeiro",
                "RJ"
        ));

        ArrayList<String> names = new ArrayList<>();
        for (Consumer con : consumerDao.findAll()) {
            names.add(con.getName());
        }

        assertEquals("Values", consumerDao.findAll().getClass().getSimpleName());
        assertEquals(beforeValue + 1, consumerDao.findAll().toArray().length);
    }
}