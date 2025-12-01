package br.com.j_fborges.dao.mocks;

import br.com.j_fborges.dao.IProductDAO;
import br.com.j_fborges.domain.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class ProductDaoMock implements IProductDAO {

    @Override
    public Boolean create(Product entity) {
        return true;
    }

    @Override
    public Integer destroy(Long id) {
        return 0;
    }

    @Override
    public Integer update(Product entity) {

        return null;
    }


    @Override
    public Product find(Long id) {
        return new Product(id.toString(), "Foo", "fygjyg86486", "10.02", "BarFoo");
    }

    @Override
    public Collection<Product> findAll() {
        return List.of();
    }

    @Override
    public void addInsertParams(PreparedStatement stm, Product product) throws SQLException, NoSuchMethodException {

    }

    @Override
    public void addUpdateParams(PreparedStatement stm, Product product) throws SQLException, NoSuchMethodException {

    }

    @Override
    public void addDeleteParams(PreparedStatement stm, Product product) throws SQLException, NoSuchMethodException {

    }

    @Override
    public void addSelectParams(PreparedStatement stm, Product product) throws SQLException, NoSuchMethodException {

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
    public String[] fieldsToStringArray(Product product, ResultSet rs) throws SQLException {
        return new String[0];
    }
}
