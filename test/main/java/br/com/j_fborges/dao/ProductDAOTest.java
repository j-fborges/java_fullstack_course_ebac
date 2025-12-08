package main.java.br.com.j_fborges.dao;

import main.java.br.com.j_fborges.domain.Product;
import org.junit.After;
import org.junit.Test;

import javax.persistence.NoResultException;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class ProductDAOTest {

    private IProductDAO productDAO;

    public ProductDAOTest() {
        this.productDAO = new ProductDAO();
    }

    @After
    public void reset(){
        List<Product> list = productDAO.findAll();
        list.forEach(p -> productDAO.delete(p));

    }

    @Test
    public void register() {
        Product product = new Product();
        product.setIdCode("H3H5K5T1");
        product.setCategory("Food");
        product.setPrice(BigDecimal.valueOf(55.22));
        product.setTitle("Soy Sauce");
        product.setDescription("You need this for your sushi");

        product = productDAO.register(product);

        assertNotNull(product);
        assertNotNull(product.getId());
    }

    @Test(expected = NoResultException.class)
    public void delete() {
        Product product = new Product();
        product.setIdCode("H3H5K5T2");
        product.setCategory("Food");
        product.setPrice(BigDecimal.valueOf(55.22));
        product.setTitle("Soy Sauce");
        product.setDescription("You need this for your sushi");

        product = productDAO.register(product);

        assertNotNull(product);
        assertNotNull(product.getId());

        productDAO.delete(product);

        assertNull(productDAO.find(product.getId()));
    }

    @Test
    public void findAll() {
        Product product = new Product();
        product.setIdCode("H3H5K5T3");
        product.setCategory("Food");
        product.setPrice(BigDecimal.valueOf(55.22));
        product.setTitle("Soy Sauce");
        product.setDescription("You need this for your sushi");

        product = productDAO.register(product);

        assertNotNull(product);
        assertNotNull(product.getId());

        Product product2 = new Product();

        product2.setIdCode("H3H5K5T4");
        product2.setCategory("Food");
        product2.setPrice(BigDecimal.valueOf(55.22));
        product2.setTitle("Sushi rice");
        product2.setDescription("You need this for your sushi");

        product2 = productDAO.register(product2);

        assertNotNull(product2);
        assertNotNull(product2.getId());

        List<Product> products = productDAO.findAll();

        assertNotNull(products);
        assertEquals(2, products.toArray().length);
    }


    @Test
    public void find() {

        Product product = new Product();
        product.setIdCode("H3H5K5T5");
        product.setCategory("Food");
        product.setPrice(BigDecimal.valueOf(55.22));
        product.setTitle("Soy Sauce");
        product.setDescription("You need this for your sushi");

        product = productDAO.register(product);

        assertNotNull(product);
        assertNotNull(product.getId());

        assertNotNull(productDAO.find(product.getId()));
        assertEquals(product.getId(), productDAO.find(product.getId()).getId());
    }

    @Test
    public void findByProductCode() {
        Product product = new Product();
        product.setIdCode("H3H5K5T5");
        product.setCategory("Food");
        product.setPrice(BigDecimal.valueOf(55.22));
        product.setTitle("Soy Sauce");
        product.setDescription("You need this for your sushi");

        product = productDAO.register(product);

        assertNotNull(product);
        assertNotNull(product.getId());

        assertNotNull(productDAO.findByProductCode(product.getIdCode()));
        assertEquals(product.getIdCode(), productDAO.findByProductCode(product.getIdCode()).getIdCode());
    }
}