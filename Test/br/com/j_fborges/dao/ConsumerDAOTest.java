package br.com.j_fborges.dao;

import br.com.j_fborges.dao.generic.GenericDAO;
import br.com.j_fborges.domain.Consumer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class ConsumerDAOTest {

    private final IConsumerDAO consumerDao;
    private Consumer consumer;
    Long nextConsumerId;

    public ConsumerDAOTest() {
        this.consumerDao = new ConsumerDAO();
    }

    @Before
    public void init() throws SQLException {

        nextConsumerId = consumerDao.getCurrSequenceIdKey() + 1;

        consumer = new Consumer(
                nextConsumerId.toString(),
                "Rodrigo",
                "123123484",
                "ro@ro.com",
                "1199999999",
                "End",
                "10",
                "São Paulo",
                "SP"
        );
        consumerDao.create(consumer);
    }

    @After
    public void reset() {
        consumerDao.destroy(nextConsumerId);
    }

    @Test
    public void find() {
        Consumer expectedResult = consumerDao.find(consumer.getId());
        Assert.assertNotNull(expectedResult);
        reset();
    }

    @Test
    public void create() throws SQLException {

        Long newNextConsumerId = consumerDao.getCurrSequenceIdKey() + 1;

        consumer.setId(newNextConsumerId);
        consumer.setEmail("foo@bar.com");
        Boolean expectedResult = consumerDao.create(consumer);
        Assert.assertTrue(expectedResult);

        consumerDao.destroy(newNextConsumerId);
        reset();
    }

    @Test
    public void destroy() {
        consumerDao.destroy(consumer.getId());
        Assert.assertNull(consumerDao.find(consumer.getIdNumber()));
    }

    @Test
    public void update() {

        Consumer expectedResult = new Consumer(
                nextConsumerId.toString(),
                "Manuel",
                "123123484",
                "bar@bar.com",
                "684643234384",
                "Rua",
                "60",
                "Rio de Janeiro",
                "RJ"
        );
        assertEquals("Rodrigo", consumerDao.find(nextConsumerId).getName());
        consumerDao.update(expectedResult);
        assertEquals("Manuel", consumerDao.find(nextConsumerId).getName());
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

        Long newNextConsumerId = consumerDao.getCurrSequenceIdKey() + 1;

        consumerDao.create(new Consumer(
                newNextConsumerId.toString(),
                "Manuel",
                "6869646948",
                "ma@foo.com",
                "684643234384",
                "Rua",
                "60",
                "Rio de Janeiro",
                "RJ"
        ));

        addCount += 1;

        assertEquals(beforeValue + addCount, consumerDao.findAll().toArray().length);

        Long newestNextConsumerId = consumerDao.getCurrSequenceIdKey() + 1;

        consumerDao.create(new Consumer(
                newestNextConsumerId.toString(),
                "Manuel",
                "6869646948",
                "ma@bar.com",
                "684643234384",
                "Rua",
                "60",
                "Rio de Janeiro",
                "RJ"
        ));

        addCount +=1;

        assertEquals(beforeValue + addCount, consumerDao.findAll().toArray().length);

        consumerDao.destroy(newNextConsumerId);
        consumerDao.destroy(newestNextConsumerId);

        Assert.assertEquals(beforeValue, (Integer) consumerDao.findAll().toArray().length);

        reset();
    }

    @Test
    public void testGetClassType() {
        ConsumerDAO consumerDao = (ConsumerDAO) this.consumerDao;
        assertEquals(Consumer.class, consumerDao.getClassType());

        reset();
    }

    @Test
    public void getCurrSequenceIdKey(){
        Collection<Consumer> consumers = consumerDao.findAll();

        Consumer lastCreatedConsumer = Collections.max(consumers, Comparator.comparing(Consumer::getId));

        assertEquals(consumerDao.getCurrSequenceIdKey(), lastCreatedConsumer.getId());
        reset();
    }

    @Test
    public void getSqlCurrSequenceId() {
        ConsumerDAO newConsumerDao = (ConsumerDAO) consumerDao;
        assertEquals("SELECT last_value FROM CONSUMER_ID_SEQ", newConsumerDao.getSqlCurrSequenceId());
        reset();
    }

    @Test
    public void getSqlInsert() {

        reset();
    }

    @Test
    public void getSqlUpdate() {
        reset();
    }

    @Test
    public void getSqlSelect() {
        reset();
    }

    @Test
    public void getSqlDelete() {
        reset();
    }

    @Test
    public void getSqlSelectAll() {
        reset();
    }

    @Test
    public void addUpdateParams() {
        reset();
    }

    @Test
    public void addInsertParams() {
        reset();
    }

    @Test
    public void addSelectParams() {
        reset();
    }

    @Test
    public void fieldsToStringArray() {
        reset();
    }
}