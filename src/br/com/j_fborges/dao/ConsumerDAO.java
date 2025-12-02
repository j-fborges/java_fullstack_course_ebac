package br.com.j_fborges.dao;

import br.com.j_fborges.dao.generic.GenericDAO;
import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.domain.Persistent;
import br.com.j_fborges.factory.ConsumerFactory;

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
    public String getSqlCurrSequenceId(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT last_value FROM CONSUMER_ID_SEQ");
        return sb.toString();
    }

    @Override
    public String getSqlInsert(){

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO TB_CONSUMERS (ID, ID_NUMBER, NAME, EMAIL, TELEPHONE, ADDRESS, ADDRESS_NUMBER, CITY, STATE) ");
        sb.append("VALUES (nextval('CONSUMER_ID_SEQ'),?,?,?,?,?,?,?,?) ");
        return sb.toString();
    }

    @Override
    public String getSqlUpdate() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE TB_CONSUMERS ");
        sb.append("SET NAME = ?, ID_NUMBER = ?, EMAIL = ?, TELEPHONE = ?, ADDRESS = ?, ADDRESS_NUMBER = ?, CITY = ?, STATE = ? ");
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
        stm.setString(3, consumer.getEmail());
        stm.setLong(4, consumer.getTel());
        stm.setString(5, consumer.getAddress());
        stm.setInt(6, consumer.getAddressNumber());
        stm.setString(7, consumer.getCity());
        stm.setString(8, consumer.getState());
        stm.setLong(9, consumer.getId());
    }

    @Override
    public void addInsertParams(PreparedStatement stm, Consumer entity) throws SQLException {

        Consumer consumer = entity;
        stm.setLong(1, consumer.getIdNumber());
        stm.setString(2, consumer.getName());
        stm.setString(3, consumer.getEmail());
        stm.setLong(4, consumer.getTel());
        stm.setString(5, consumer.getAddress());
        stm.setInt(6, consumer.getAddressNumber());
        stm.setString(7, consumer.getCity());
        stm.setString(8, consumer.getState());
    }

    @Override
    public void addSelectParams(PreparedStatement stm, Long id) throws SQLException {
        stm.setLong(1, id);
    }

    @Override
    public String[] fieldsToStringArray(ResultSet rs) throws SQLException {
        Long id = rs.getLong("ID");
        Long idNumber = rs.getLong("ID_NUMBER");
        String name = rs.getString("NAME");
        String email = rs.getString("EMAIL");
        Long tel = rs.getLong("TELEPHONE");
        String address = rs.getString("ADDRESS");
        Integer addressNumber = rs.getInt("ADDRESS_NUMBER");
        String city = rs.getString("CITY");
        String state = rs.getString("STATE");


        return new String[]{id.toString(), name,idNumber.toString(), email, tel.toString(), address, addressNumber.toString(), city, state};
    }

    @Override
    public Consumer factoryCreateObject(String [] inputs){
        ConsumerFactory factory = new ConsumerFactory();
        return (Consumer) factory.createObject(inputs);
    }
}
