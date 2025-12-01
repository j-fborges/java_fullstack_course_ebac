package br.com.j_fborges.dao;

import br.com.j_fborges.domain.Product;
import br.com.j_fborges.exception.TypeKeyNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class ProductDAOTest {

    Product product;
    IProductDAO productDAO;
    Long nextProductId;

    @Before
    public void init() throws SQLException {
        this.productDAO = new ProductDAO();
        nextProductId = productDAO.getCurrSequenceIdKey() + 1;
        this.product = new Product(nextProductId.toString(), "Bacon Strips", "k21w32", "22.04", "Delicious bacon strips");
        this.productDAO.create(this.product);
    }

    private void reset() {
        productDAO.destroy(nextProductId);
    }

    @Test
    public void getClassType() {
        assertEquals("ProductDAO", productDAO.getClass().getSimpleName());
        reset();
    }

    @Test
    public void updateData() {

        Product expected = new Product(productDAO.getCurrSequenceIdKey().toString(), "Corn Starch", "k21w32", "16.04", "The ingredient you need");
        Product notExpected = new Product("0", "Mapple Syrup", "u87t14", "10.21", "Make nice drinks");

        productDAO.update(expected);
        productDAO.update(notExpected);
        assertEquals("Corn Starch", productDAO.find(product.getId()).getTitle());
        assertNull(productDAO.find(notExpected.getId()));

        reset();
    }
}