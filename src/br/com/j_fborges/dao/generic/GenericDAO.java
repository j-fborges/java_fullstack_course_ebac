package br.com.j_fborges.dao.generic;

import br.com.j_fborges.annotation.MatchingDAOClass;
import br.com.j_fborges.annotation.MatchingPersistentFactory;
import br.com.j_fborges.domain.Persistent;
import br.com.j_fborges.functionalInterface.TriFunction;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.function.BiFunction;

public abstract class GenericDAO<T extends Persistent, E extends Serializable> implements IGenericDAO<T, E> {

    TriFunction<T, Connection, PreparedStatement, ResultSet> getCurrentIdKeyFromSequenceThunk = (entity, connection, stm) -> {

        connection = ConnectionFactory.getConnection();
        String sql = getSqlCurrSequenceId();
        stm = connection.prepareStatement(sql);
        return stm.executeQuery();
    };

    TriFunction<T, Connection, PreparedStatement, Integer> createThunk = (entity, connection, stm) -> {

        connection = ConnectionFactory.getConnection();

        String sql = getSqlInsert();
        stm = connection.prepareStatement(sql);
        addInsertParams(stm, entity);
        return stm.executeUpdate();
    };
    TriFunction<T, Connection, PreparedStatement, Integer> updateThunk = (entity, connection, stm) -> {

        connection = ConnectionFactory.getConnection();

        String sql = getSqlUpdate();
        stm = connection.prepareStatement(sql);
        addUpdateParams(stm, entity);
        return stm.executeUpdate();
    };
    TriFunction<T, Connection, PreparedStatement, ResultSet> selectThunk = (entity, connection, stm) -> {

        connection = ConnectionFactory.getConnection();
        String sql = getSqlSelect();
        stm = connection.prepareStatement(sql);
        addSelectParams(stm, entity);
        return stm.executeQuery();
    };
    TriFunction<T, Connection, PreparedStatement, Integer> deleteThunk = (entity, connection, stm) -> {

        connection = ConnectionFactory.getConnection();
        String sql = getSqlDelete();
        stm = connection.prepareStatement(sql);
        addSelectParams(stm, entity);
        return stm.executeUpdate();
    };
    TriFunction<T, Connection, PreparedStatement, ResultSet> findAllThunk = (entity, connection, stm) -> {

        connection = ConnectionFactory.getConnection();
        String sql = getSqlSelectAll();
        stm = connection.prepareStatement(sql);
        return stm.executeQuery();
    };
    BiFunction<T, TriFunction<T, Connection, PreparedStatement, ?>, ?> sqlConnectAndQuery = (persistent, function) -> {
        Connection connection = null;
        PreparedStatement stm = null;

        try {
            return functionReceiver(persistent, connection, stm, function);
        } catch (SQLException e) {
            System.out.println(e.getCause());
            throw new RuntimeException("");
        } finally {
            closeConnection(connection, stm, null);
        }
    };

    public GenericDAO() {

    }

    public abstract Class<T> getClassType();

    public abstract void updateData(T entity, T entityRegistered);

    @Override
    public Boolean create(T entity) throws SQLException {
        Integer result = (Integer) sqlConnectAndQuery.apply(entity, createThunk);
        return result >= 0;
    }

    @Override
    public Integer destroy(Long id) {
        Persistent entity = getGenericTypeClass();
        entity.setId(id);
        return (Integer) sqlConnectAndQuery.apply((T) entity, deleteThunk);
    }

    @Override
    public Integer update(T entity) {

        return (Integer) sqlConnectAndQuery.apply(entity, updateThunk);
    }

    @Override
    public T find(Long id) {

        Persistent entity = getGenericTypeClass();
        entity.setId(id);
        ResultSet result = (ResultSet) sqlConnectAndQuery.apply((T) entity, selectThunk);
        T newEntity = null;

        try {

            while (result.next()) {
                String[] fieldArray = getMatchingFieldsToStringArray(result);
                newEntity = getMatchingPersistentFactory(fieldArray);
            }

            return newEntity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<T> findAll() {
        T entity = getGenericTypeClass();
        System.out.println(entity);
        ResultSet result = (ResultSet) sqlConnectAndQuery.apply(entity, findAllThunk);
        List<T> list = new ArrayList<>();
        T newEntity = null;

        try {

            while (result.next()) {
                String[] fieldArray = getMatchingFieldsToStringArray(result);
                newEntity = getMatchingPersistentFactory(fieldArray);


                list.add(newEntity);
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long getCurrSequenceIdKey() {
        T entity = getGenericTypeClass();
        Long val = null;
        ResultSet result = (ResultSet) sqlConnectAndQuery.apply(entity, getCurrentIdKeyFromSequenceThunk);

        try {
            if (result.next()) {
                val = result.getLong(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return val;
    }

    @Override
    public String getSqlInsert() {
        return getChildClassSQLParams("getSqlInsert");
    }

    @Override
    public String getSqlUpdate() {
        return getChildClassSQLParams("getSqlUpdate");
    }

    @Override
    public String getSqlDelete() {
        return getChildClassSQLParams("getSqlDelete");
    }

    @Override
    public String getSqlSelect() {
        return getChildClassSQLParams("getSqlSelect");
    }

    @Override
    public String getSqlSelectAll() {
        return getChildClassSQLParams("getSqlSelectAll");
    }

    @Override
    public String getSqlCurrSequenceId() {
        return getChildClassSQLParams("getSqlNextSequenceId");
    }

    @Override
    public void addInsertParams(PreparedStatement stm, T entity) throws SQLException {
        invokeSqlChildClassMethod("addInsertParams", stm, entity);
    }

    @Override
    public void addUpdateParams(PreparedStatement stm, T entity) throws SQLException {
        invokeSqlChildClassMethod("addUpdateParams", stm, entity);
    }

    @Override
    public void addDeleteParams(PreparedStatement stm, T entity) throws SQLException {
        invokeSqlChildClassMethod("addDeleteParams", stm, entity);
    }

    @Override
    public void addSelectParams(PreparedStatement stm, T entity) throws SQLException {
        invokeSqlChildClassMethod("addSelectParams", stm, entity);
    }

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

    private <R> R functionReceiver(T persistent, Connection connection, PreparedStatement stm, TriFunction<T, Connection, PreparedStatement, R> function) throws SQLException {
        return function.apply(persistent, connection, stm);
    }

    private void invokeSqlChildClassMethod(String methodName, PreparedStatement stm, T entity) {
        String entityMatchingDAOClassName = getMatchingDAOChildClass(entity);

        try {
            Class childDAOClass = Class.forName(entityMatchingDAOClassName);
            Method method = childDAOClass.getMethod(methodName, PreparedStatement.class, entity.getClass());
            method.invoke(childDAOClass.newInstance(), stm, entity);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }
    }

    private String getChildClassSQLParams(String methodName) {
        T entity = getGenericTypeClass();

        String entityMatchingDAOClassName = getMatchingDAOChildClass(entity);
        String sqlString = "";
        try {
            Class childDAOClass = Class.forName(entityMatchingDAOClassName);
            Method method = childDAOClass.getMethod(methodName);
            sqlString = (String) method.invoke(childDAOClass.newInstance(), entity);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException |
                 InstantiationException e) {
            throw new RuntimeException(e);
        }

        return sqlString;
    }

    private String getMatchingDAOChildClass(T entity) {
        return entity.getClass().getAnnotation(MatchingDAOClass.class).value();
    }

    private T getMatchingPersistentFactory(String[] inputs) {
        T entity = getGenericTypeClass();

        String factoryClassName = entity.getClass().getAnnotation(MatchingPersistentFactory.class).value();
        try {
            Class persistentFactory = Class.forName(factoryClassName);

            Method method = persistentFactory.getMethod("createObject", String[].class);

            T persistent = (T) method.invoke(persistentFactory.newInstance(), (Object) inputs);

            return persistent;

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException |
                 InstantiationException e) {
            e.getCause();
            throw new RuntimeException(e);
        }
    }

    private String[] getMatchingFieldsToStringArray(ResultSet rs) {
        T entity = getGenericTypeClass();

        String entityMatchingDAOClassName = getMatchingDAOChildClass(entity);
        String[] fieldArray;
        try {
            Class childDAOClass = Class.forName(entityMatchingDAOClassName);
            Method method = childDAOClass.getMethod("fieldsToStringArray", entity.getClass(), ResultSet.class);
            fieldArray = (String[]) method.invoke(childDAOClass.newInstance(), entity, rs);
            return fieldArray;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException |
                 InstantiationException e) {
            System.out.println(e.getCause());
            throw new RuntimeException(e);
        }
    }

    private T getGenericTypeClass() {
        try {
            T entity = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
            return entity;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
