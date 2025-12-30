package br.com.j_fborges.dao;

import br.com.j_fborges.domain.Consumer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class ConsumerDAOTest {

    private final IConsumerDAO consumerDao;
    private Consumer consumer;

    public ConsumerDAOTest() {
        this.consumerDao = new ConsumerDAO();
    }

    @Before
    public void init() throws SQLException {

        consumer = new Consumer(
                "Rodrigo",
                "123123484",
                "ro@ro.com",
                "1199999999",
                "End",
                "10",
                "São Paulo",
                "SP"
        );
        consumer = consumerDao.create(consumer);
    }

    @After
    public void reset() {
        consumerDao.destroy(consumer);
    }

    @Test
    public void find() {
        Consumer expectedResult = consumerDao.find(consumer.getId());
        Assert.assertNotNull(expectedResult);
        reset();
    }

    @Test
    public void create() throws SQLException {

        Consumer otherConsumer = new Consumer(
                "Rodrigo",
                "12312348111424",
                "ro@ro.com",
                "1199999999",
                "End",
                "10",
                "São Paulo",
                "SP"
        );
        otherConsumer.setEmail("foo@bar.com");
        Consumer expectedResult = consumerDao.create(otherConsumer);
        Assert.assertNotNull(expectedResult);
        Assert.assertNotNull(expectedResult.getId());
        Assert.assertNotEquals(expectedResult.getId(), consumer.getId());
        consumerDao.destroy(expectedResult);
        reset();
    }

    @Test
    public void destroy() {
        consumerDao.destroy(consumer);
        Assert.assertNull(consumerDao.find(consumer.getIdNumber()));
    }

    @Test
    public void update() {

        Consumer expectedResult = new Consumer(
                consumer.getId().toString(),
                "Manuel",
                "123166623484777",
                "bar@bar.com",
                "684643234384",
                "Rua",
                "60",
                "Rio de Janeiro",
                "RJ"
        );

        assertEquals("Rodrigo", consumerDao.find(consumer.getId()).getName());
        consumerDao.update(expectedResult);
        assertEquals("Manuel", consumerDao.find(consumer.getId()).getName());
        reset();
    }

    @org.junit.Test
    public void getClassType() {
        assertEquals("ConsumerDAO", consumerDao.getClass().getSimpleName());
        reset();
    }

    @Test
    public void findAll() throws SQLException {

        Integer beforeValue = consumerDao.findAll().toArray().length;

        Integer addCount = 0;

        Consumer newConsumer = consumerDao.create(new Consumer(
                "Manuel",
                "6869646949658",
                "ma@foo.com",
                "684643234384",
                "Rua",
                "60",
                "Rio de Janeiro",
                "RJ"
        ));

        addCount += 1;

        assertEquals(beforeValue + addCount, consumerDao.findAll().toArray().length);

        Consumer newestConsumer = consumerDao.create(new Consumer(
                "Manuel",
                "6869000646948",
                "ma@bar.com",
                "684643234384",
                "Rua",
                "60",
                "Rio de Janeiro",
                "RJ"
        ));

        addCount +=1;

        assertEquals(beforeValue + addCount, consumerDao.findAll().toArray().length);

        consumerDao.destroy(newConsumer);
        consumerDao.destroy(newestConsumer);

        Assert.assertEquals(beforeValue, (Integer) consumerDao.findAll().toArray().length);

        reset();
    }

    @Test
    public void testGetClassType() {
        ConsumerDAO consumerDao = (ConsumerDAO) this.consumerDao;
        assertEquals(Consumer.class, consumerDao.getClassType());

        reset();
    }
}