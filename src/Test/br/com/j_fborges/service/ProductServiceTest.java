package br.com.j_fborges.service;

import br.com.j_fborges.dao.IProductDAO;
import br.com.j_fborges.dao.mocks.ProductDaoMock;
import br.com.j_fborges.domain.Product;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class ProductServiceTest {

    private IProductService productService;

    private Product product;

    public ProductServiceTest() {
        IProductDAO dao = new ProductDaoMock();
        productService = new ProductService(dao);
    }

    @Before
    public void init() {
        product = new Product("999", "Product 1", "A1", "10.32", "Foo", "Product 1");

    }

    @Test
    public void find() {
        Product productr = this.productService.find(product.getId());
        Assert.assertNotNull(productr);
    }

    @Test
    public void register() throws SQLException {
        Product expected = productService.register(product);
        Assert.assertNotNull(expected);
    }

    @Test
    public void delete() {
        productService.delete(product);
    }

    @Test
    public void updateProduct() {
        product.setTitle("Product 2");
        productService.update(product);

        Assert.assertEquals("Product 2", product.getTitle());
    }
}