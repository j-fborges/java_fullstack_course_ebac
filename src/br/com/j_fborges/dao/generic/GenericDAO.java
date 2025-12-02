package br.com.j_fborges.dao.generic;

import br.com.j_fborges.domain.Persistent;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class GenericDAO<T extends Persistent, E extends Serializable> implements IGenericDAO<T, E> {

    public GenericDAO() {

    }

    public abstract Class<T> getClassType();

    @Override
    public Boolean create(T entity) {
        Integer result = null;

        Connection connection = null;
        PreparedStatement stm = null;

        try {
            connection = ConnectionFactory.getConnection();

            String sql = getSqlInsert();
            stm = connection.prepareStatement(sql);
            addInsertParams(stm, entity);
            result = stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getCause());
            throw new RuntimeException("Error inserting entry to table");
        } finally {
            closeConnection(connection, stm, null);
        }
        return result >= 0;
    }

    @Override
    public Integer destroy(Long id) {

        Connection connection = null;
        PreparedStatement stm = null;
        Integer result = null;

        try {

            connection = ConnectionFactory.getConnection();
            String sql = getSqlDelete();
            stm = connection.prepareStatement(sql);
            addSelectParams(stm, id);
            result = stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getCause());
            throw new RuntimeException("");
        } finally {
            closeConnection(connection, stm, null);
        }
        return result;
    }

    @Override
    public Integer update(T entity) {

        Connection connection = null;
        PreparedStatement stm = null;
        Integer result = null;

        try {
            connection = ConnectionFactory.getConnection();

            String sql = getSqlUpdate();
            stm = connection.prepareStatement(sql);
            addUpdateParams(stm, entity);
            result = stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getCause());
            throw new RuntimeException("");
        } finally {
            closeConnection(connection, stm, null);
        }

        return result;
    }

    @Override
    public T find(Long id) {

        Connection connection = null;
        PreparedStatement stm = null;
        ResultSet result = null;
        T newEntity = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlSelect();
            stm = connection.prepareStatement(sql);
            addSelectParams(stm, id);
            result = stm.executeQuery();

            while (result.next()) {
                String[] fieldArray = fieldsToStringArray(result);
                newEntity = factoryCreateObject(fieldArray);
            }

        } catch (SQLException e) {
            System.out.println(e.getCause());
            throw new RuntimeException("");
        } finally {
            closeConnection(connection, stm, result);
        }

        return newEntity;
    }

    @Override
    public Collection<T> findAll() {
        Connection connection = null;
        PreparedStatement stm = null;

        ResultSet result = null;
        List<T> list = new ArrayList<>();
        T newEntity = null;


        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlSelectAll();
            stm = connection.prepareStatement(sql);
            result = stm.executeQuery();
            while (result.next()) {
                String[] fieldArray = fieldsToStringArray(result);
                newEntity = factoryCreateObject(fieldArray);


                list.add(newEntity);
            }
        } catch (SQLException e) {
            System.out.println(e.getCause());
            throw new RuntimeException("");
        } finally {
            closeConnection(connection, stm, result);
        }

        return list;
    }

    @Override
    public Long getCurrSequenceIdKey() {
        Connection connection = null;
        PreparedStatement stm = null;
        Long val = null;
        ResultSet result = null;

        try {
            connection = ConnectionFactory.getConnection();
            String sql = getSqlCurrSequenceId();
            stm = connection.prepareStatement(sql);
            result = stm.executeQuery();

            if (result.next()) {
                val = result.getLong(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getCause());
            throw new RuntimeException("Error getting current sequence id key");
        } finally {
            closeConnection(connection, stm, result);
        }
        return val;
    }

    protected abstract String getSqlInsert();

    protected abstract String getSqlUpdate();

    protected abstract String getSqlDelete();

    protected abstract String getSqlSelect();

    protected abstract String getSqlSelectAll();

    protected abstract String getSqlCurrSequenceId();

    protected abstract void addInsertParams(PreparedStatement stm, T entity) throws SQLException;

    protected abstract void addUpdateParams(PreparedStatement stm, T entity) throws SQLException;

    protected abstract void addSelectParams(PreparedStatement stm, Long id) throws SQLException;

    protected abstract String[] fieldsToStringArray( ResultSet rs ) throws SQLException;

    protected abstract T factoryCreateObject(String[] inputs);

    private void closeConnection(Connection connection, PreparedStatement stm, ResultSet rs) {
        try {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (stm != null && !stm.isClosed()) {
                stm.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
