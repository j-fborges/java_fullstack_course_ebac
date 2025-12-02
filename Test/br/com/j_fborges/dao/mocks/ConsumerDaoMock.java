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
    public Boolean create(Consumer entity) {
        return true;
    }

    @Override
    public Integer destroy(Long value) {

        return null;
    }

    @Override
    public Integer update(Consumer entity) {

        return null;
    }

    @Override
    public Consumer find(Long value) {
        Consumer consumer = new Consumer(value.toString(), "Foo", "9985585658","88999999", "Bar", "666", "Bar", "Bar");
        return consumer;
    }

    @Override
    public Collection<Consumer> findAll() {
        return List.of();
    }

    @Override
    protected void addInsertParams(PreparedStatement stm, Consumer consumer) throws SQLException {

    }

    @Override
    public void addUpdateParams(PreparedStatement stm, Consumer consumer) throws SQLException, NoSuchMethodException {

    }

    @Override
    public void addDeleteParams(PreparedStatement stm, Consumer consumer) throws SQLException, NoSuchMethodException {

    }

    @Override
    public void addSelectParams(PreparedStatement stm, Consumer consumer) throws SQLException, NoSuchMethodException {

    }

    @Override
    public String getSqlInsert() {
        return "";
    }

    @Override
    public String getSqlUpdate() {
        return "";
    }

    @Override
    public String getSqlDelete() {
        return "";
    }

    @Override
    public String getSqlSelect() {
        return "";
    }

    @Override
    public String getSqlSelectAll() {
        return "";
    }

    @Override
    public String getSqlCurrSequenceId() {
        return "";
    }

    @Override
    public Long getCurrSequenceIdKey() {
        return 0L;
    }

    @Override
    public String[] fieldsToStringArray(Consumer consumer, ResultSet rs) throws SQLException {
        return new String[0];
    }
}
