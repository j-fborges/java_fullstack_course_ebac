package br.com.j_fborges.dao;

import br.com.j_fborges.dao.generic.GenericDAO;
import br.com.j_fborges.domain.Sale;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleDAO extends GenericDAO<Sale, String> implements ISaleDao {


    @Override
    public void completeSale(Sale sale) {
        sale.setStatus(Sale.Status.COMPLETED);
        super.update(sale);
    }

    @Override
    public Class<Sale> getClassType() {
        return Sale.class;
    }

    @Override
    public Integer destroy(Long id) {
        throw new UnsupportedOperationException("OPERATION NOT PERMITTED");
    }

    @Override
    protected String getSqlInsert() {
        return "";
    }

    @Override
    protected String getSqlUpdate() {
        return "";
    }

    @Override
    protected String getSqlDelete() {
        return "";
    }

    @Override
    protected String getSqlSelect() {
        return "";
    }

    @Override
    protected String getSqlSelectAll() {
        return "";
    }

    @Override
    protected String getSqlCurrSequenceId() {
        return "";
    }

    @Override
    protected void addInsertParams(PreparedStatement stm, Sale sale) throws SQLException {

    }

    @Override
    protected void addUpdateParams(PreparedStatement stm, Sale sale) throws SQLException {

    }

    @Override
    protected void addSelectParams(PreparedStatement stm, Long id) throws SQLException {

    }

    @Override
    public String[] fieldsToStringArray(ResultSet rs) throws SQLException {
        return new String[0];
    }

    @Override
    protected Sale factoryCreateObject(String[] inputs) {
        return null;
    }
}
