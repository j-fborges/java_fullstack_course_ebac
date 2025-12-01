package br.com.j_fborges.dao;

import br.com.j_fborges.dao.generic.GenericDAO;
import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.domain.Persistent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsumerDAO extends GenericDAO<Consumer, Long> implements IConsumerDAO {

    public ConsumerDAO(){
        super();
    }

    @Override
    public Class<Consumer> getClassType() {
        return Consumer.class;
    }

    @Override
    public void updateData(Consumer consumer, Consumer entityRegistered) {
        entityRegistered.setName(consumer.getName());
        entityRegistered.setTel(consumer.getTel());
        entityRegistered.setAddressNumber(consumer.getAddressNumber());
        entityRegistered.setAddress(consumer.getAddress());
        entityRegistered.setCity(consumer.getCity());
        entityRegistered.setState(consumer.getState());
    }

    @Override
    public String getSqlCurrSequenceId(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT last_value FROM CONSUMER_ID_SEQ");
        return sb.toString();
    }

    @Override
    public String getSqlInsert(){

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO TB_CONSUMERS (ID, ID_NUMBER, NAME, TELEPHONE, ADDRESS, ADDRESS_NUMBER, CITY, STATE) ");
        sb.append("VALUES (nextval('CONSUMER_ID_SEQ'),?,?,?,?,?,?,?) ");
        return sb.toString();
    }

    @Override
    public String getSqlUpdate() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE TB_CONSUMERS ");
        sb.append("SET NAME = ?, ID_NUMBER = ?, TELEPHONE = ?, ADDRESS = ?, ADDRESS_NUMBER = ?, CITY = ?, STATE = ? ");
        sb.append("WHERE ID = ?");
        return sb.toString();
    }

    @Override
    public String getSqlSelect() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM TB_CONSUMERS ");
        sb.append("WHERE ID = ?");
        return sb.toString();
    }

    @Override
    public String getSqlDelete() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM TB_CONSUMERS ");
        sb.append("WHERE ID = ?");
        return sb.toString();
    }

    @Override
    public String getSqlSelectAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM TB_CONSUMERS");
        return sb.toString();
    }

    @Override
    public void addUpdateParams(PreparedStatement stm, Consumer entity) throws SQLException {
        Consumer consumer = entity;
        stm.setString(1, consumer.getName());
        stm.setLong(2, consumer.getIdNumber());
        stm.setLong(3, consumer.getTel());
        stm.setString(4, consumer.getAddress());
        stm.setInt(5, consumer.getAddressNumber());
        stm.setString(6, consumer.getCity());
        stm.setString(7, consumer.getState());
        stm.setLong(8, consumer.getId());
    }

    @Override
    public void addInsertParams(PreparedStatement stm, Consumer entity) throws SQLException {

        Consumer consumer = entity;
        stm.setLong(1, consumer.getIdNumber());
        stm.setString(2, consumer.getName());
        stm.setLong(3, consumer.getTel());
        stm.setString(4, consumer.getAddress());
        stm.setInt(5, consumer.getAddressNumber());
        stm.setString(6, consumer.getCity());
        stm.setString(7, consumer.getState());
    }

    @Override
    public void addSelectParams(PreparedStatement stm, Consumer entity) throws SQLException {
        stm.setLong(1, entity.getId());
    }

    @Override
    public String[] fieldsToStringArray(Consumer entity, ResultSet rs) throws SQLException {
        Long id = rs.getLong("ID");
        Long idNumber = rs.getLong("ID_NUMBER");
        String name = rs.getString("NAME");
        Long tel = rs.getLong("TELEPHONE");
        String address = rs.getString("ADDRESS");
        Integer addressNumber = rs.getInt("ADDRESS_NUMBER");
        String city = rs.getString("CITY");
        String state = rs.getString("STATE");


        return new String[]{id.toString(), name, idNumber.toString(), tel.toString(), address, addressNumber.toString(), city, state};
    }
}
