package br.com.j_fborges.service;

import br.com.j_fborges.dao.IProductDAO;
import br.com.j_fborges.dao.mocks.ProductDaoMock;
import br.com.j_fborges.domain.Product;
import br.com.j_fborges.exception.TypeKeyNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ProductServiceTest {

    private IProductService productService;

    private Product product;

    public ProductServiceTest() {
        IProductDAO dao = new ProductDaoMock();
        productService = new ProductService(dao);
    }

    @Before
    public void init() {
        product = new Product("Product 1", "A1", "10.32", "Product 1");

    }

    @Test
    public void findByIdCode() {
        Product productr = this.productService.findByIdCode(product.getIdCode());
        Assert.assertNotNull(productr);
    }

    @Test
    public void register() throws TypeKeyNotFoundException {
        Boolean expected = productService.register(product);
        Assert.assertTrue(expected);
    }

    @Test
    public void excluir() {
        productService.delete(product.getIdCode());
    }

    @Test
    public void alterarCliente() throws TypeKeyNotFoundException {
        product.setTitle("Product 2");
        productService.update(product);

        Assert.assertEquals("Product 2", product.getTitle());
    }
}