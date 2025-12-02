package br.com.j_fborges.dao;

import br.com.j_fborges.dao.generic.GenericDAO;
import br.com.j_fborges.domain.Persistent;
import br.com.j_fborges.domain.Product;
import br.com.j_fborges.factory.ProductFactory;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProductDAO extends GenericDAO<Product, String> implements IProductDAO {


    public ProductDAO() {
        super();
    }

    @Override
    public Class<Product> getClassType() {
        return Product.class;
    }

    @Override
    public void updateData(Product entity, Product entityRegistered) {
        entityRegistered.setIdCode(entity.getIdCode());
        entityRegistered.setDescription(entity.getDescription());
        entityRegistered.setTitle(entity.getTitle());
        entityRegistered.setPrice(entity.getPrice());
    }

    @Override
    public String getSqlCurrSequenceId(){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT last_value FROM PRODUCT_ID_SEQ");
        return sb.toString();
    }

    @Override
    public String getSqlInsert(){

        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO TB_PRODUCTS (ID, ID_CODE, TITLE, PRICE, CATEGORY DESCRIPTION) ");
        sb.append("VALUES (nextval('PRODUCT_ID_SEQ'),?,?,?,?) ");
        return sb.toString();
    }

    @Override
    public String getSqlUpdate() {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE TB_PRODUCTS ");
        sb.append("SET TITLE = ?, ID_CODE = ?, PRICE = ?, CATEGORY = ?, DESCRIPTION = ? ");
        sb.append("WHERE ID = ?");
        return sb.toString();
    }

    @Override
    public String getSqlSelect() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM TB_PRODUCTS ");
        sb.append("WHERE ID = ?");
        return sb.toString();
    }

    @Override
    public String getSqlDelete() {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM TB_PRODUCTS ");
        sb.append("WHERE ID = ?");
        return sb.toString();
    }

    @Override
    public String getSqlSelectAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM TB_PRODUCTS");
        return sb.toString();
    }

    @Override
    public void addUpdateParams(PreparedStatement stm, Product entity) throws SQLException {
        Product product = entity;
        stm.setString(1, product.getTitle());
        stm.setString(2, product.getIdCode());
        stm.setBigDecimal(3, product.getPrice());
        stm.setString(4, product.getDescription());
        stm.setLong(5, product.getId());
    }

    @Override
    public void addInsertParams(PreparedStatement stm, Product entity) throws SQLException {

        Product product = entity;
        stm.setString(1, product.getIdCode());
        stm.setString(2, product.getTitle());
        stm.setBigDecimal(3, product.getPrice());
        stm.setString(4, product.getCategory());
        stm.setString(5, product.getDescription());
    }

    @Override
    public void addSelectParams(PreparedStatement stm, Long id) throws SQLException {
        stm.setLong(1, id);
    }
    
    @Override
    public String[] fieldsToStringArray(ResultSet rs) throws SQLException {
        Long id = rs.getLong("ID");
        String title = rs.getString("TITLE");
        String idCode = rs.getString("ID_CODE");
        BigDecimal price = rs.getBigDecimal("PRICE");
        String category = rs.getString("CATEGORY");
        String description = rs.getString("DESCRIPTION");

        return new String[]{id.toString(), title, idCode.toString(), price.toString(), category, description};
    }

    @Override
    protected Product factoryCreateObject(String[] inputs) {
        ProductFactory factory = new ProductFactory();
        return (Product) factory.createObject(inputs);
    }
}
