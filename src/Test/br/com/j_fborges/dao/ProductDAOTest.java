package br.com.j_fborges.dao;

import br.com.j_fborges.domain.Product;
import br.com.j_fborges.exception.TypeKeyNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class ProductDAOTest {

    Product product;
    IProductDAO productDAO;

    @Before
    public void init() throws SQLException {
        this.productDAO = new ProductDAO();;
        this.product = new Product( "Bacon Strips", "k21w3277471", "22.04", "Food", "Delicious bacon strips");
        this.productDAO.create(this.product);
    }

    @After
    public void reset() {
        productDAO.destroy(this.product);
    }

    @Test
    public void getClassType() {
        ProductDAO newProductDao = (ProductDAO) productDAO;
        assertEquals(Product.class, newProductDao.getClassType());
        reset();
    }

    @Test
    public void updateData() {

        Product expected = new Product(this.product.getId().toString(),"Corn Starch", "k21w326657", "16.04", "Food", "The ingredient you need");

        productDAO.update(expected);
        assertEquals("Corn Starch", productDAO.find(product.getId()).getTitle());

        reset();
    }
}