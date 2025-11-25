package br.com.j_fborges.dao;

import br.com.j_fborges.domain.Product;
import br.com.j_fborges.exception.TypeKeyNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductMapDAOTest {

    Product product;
    IProductDAO productDAO;

    @Before
    public void init() throws TypeKeyNotFoundException {
        this.product = new Product("Bacon Strips", "k21w32", "22.04", "Delicious bacon strips");
        this.productDAO = new ProductMapDAO();
        this.productDAO.create(this.product);
    }

    @Test
    public void getClassType() {
        assertEquals("ProductMapDAO", productDAO.getClass().getSimpleName());
    }

    @Test
    public void updateData() throws TypeKeyNotFoundException {
        Product expected = new Product("Corn Starch", "k21w32", "16.04", "The ingredient you need");
        Product notExpected = new Product("Mapple Syrup", "u87t14", "10.21", "Make nice drinks");

        productDAO.update(expected);
        productDAO.update(notExpected);
        assertEquals("Corn Starch", productDAO.find(product.getIdCode()).getTitle());
        assertNull(productDAO.find(notExpected.getIdCode()));
    }
}