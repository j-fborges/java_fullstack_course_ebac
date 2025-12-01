package br.com.j_fborges.dao.generic;

import br.com.j_fborges.domain.Persistent;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public interface IGenericDAO <T extends Persistent, E extends Serializable> {

    public Boolean create(T entity) throws SQLException;

    public Integer destroy(Long id);

    public Integer update(T entity);

    public T find(Long id);

    public Collection<T> findAll();

    void addInsertParams(PreparedStatement stm, T entity) throws SQLException, NoSuchMethodException;

    void addUpdateParams(PreparedStatement stm, T entity) throws SQLException, NoSuchMethodException;

    void addDeleteParams(PreparedStatement stm, T entity) throws SQLException, NoSuchMethodException;

    void addSelectParams(PreparedStatement stm, T entity) throws SQLException, NoSuchMethodException;

    String getSqlInsert();

    String getSqlUpdate();

    String getSqlDelete();

    String getSqlSelect();

    String getSqlSelectAll();

    String getSqlCurrSequenceId();

    Long getCurrSequenceIdKey();

    public String[] fieldsToStringArray(T entity, ResultSet rs) throws SQLException;
}
