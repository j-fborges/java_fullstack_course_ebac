package br.com.j_fborges.dao;

import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.domain.Product;
import br.com.j_fborges.domain.Sale;
import br.com.j_fborges.exception.TypeKeyNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.junit.Assert.*;

public class SaleMapDAOTest {

    @Test
    public void getClassType() {
    }

    @Test
    public void updateData() {
    }

    @Test
    public void destroy() {
    }

    private ISaleDao saleDao;

    private IConsumerDAO consumerDao;

    private IProductDAO productDao;

    //private Sale sale;

    private Consumer consumer;

    private Product product;

    public SaleMapDAOTest() {
        saleDao = new SaleMapDAO();
        consumerDao = new ConsumerMapDAO();
        productDao = new ProductMapDAO();
    }

    @Before
    public void init() throws TypeKeyNotFoundException {
        this.consumer = createConsumer();
        this.product = createProduct("A1", BigDecimal.TEN);
    }


    @Test
    public void find() throws TypeKeyNotFoundException {
        Sale sale = createSale("A1");
        Boolean expected = saleDao.create(sale);
        assertTrue(expected);
        Sale foundSale = saleDao.find(sale.getCode());
        assertNotNull(foundSale);
        assertEquals(sale.getCode(), foundSale.getCode());
    }

    @Test
    public void create() throws TypeKeyNotFoundException {
        Sale sale = createSale("A2");
        Boolean expected = saleDao.create(sale);
        assertTrue(expected);
        assertTrue(sale.getTotalValue().equals(BigDecimal.valueOf(20)));
        assertTrue(sale.getStatus().equals(Sale.Status.STARTED));
    }


    @Test
    public void cancelSale() throws TypeKeyNotFoundException {
        String codeSale = "A3";
        Sale sale = createSale(codeSale);
        Boolean expected = saleDao.create(sale);
        assertTrue(expected);
        assertNotNull(sale);
        assertEquals(codeSale, sale.getCode());

        sale.setStatus(Sale.Status.CANCELLED);
        saleDao.update(sale);

        Sale foundSale = saleDao.find(codeSale);
        assertEquals(codeSale, foundSale.getCode());
        assertEquals(Sale.Status.CANCELLED, foundSale.getStatus());
    }

    @Test
    public void addProductQuantity() throws TypeKeyNotFoundException {
        String codeSale = "A4";
        Sale sale = createSale(codeSale);
        Boolean expected = saleDao.create(sale);
        assertTrue(expected);
        assertNotNull(sale);
        assertEquals(codeSale, sale.getCode());

        Sale foundSale = saleDao.find(codeSale);
        foundSale.addProductQuantity(product, 1);

        assertTrue(sale.getQuantityTotalProducts() == 3);
        assertTrue(sale.getTotalValue().equals(BigDecimal.valueOf(30)));
        assertTrue(sale.getStatus().equals(Sale.Status.STARTED));
    }

    @Test
    public void addDifferentProductQuantity() throws TypeKeyNotFoundException {
        String codeSale = "A5";
        Sale sale = createSale(codeSale);
        Boolean expected = saleDao.create(sale);
        assertTrue(expected);
        assertNotNull(sale);
        assertEquals(codeSale, sale.getCode());

        Product prod = createProduct(codeSale, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codeSale, prod.getIdCode());

        Sale foundSale = saleDao.find(codeSale);
        foundSale.addProductQuantity(prod, 1);

        assertTrue(sale.getQuantityTotalProducts() == 3);
        assertTrue(sale.getTotalValue().equals(BigDecimal.valueOf(70)));
        assertTrue(sale.getStatus().equals(Sale.Status.STARTED));
    }

    @Test
    public void doesNotOverwriteExistingProduct() throws TypeKeyNotFoundException {
        Sale sale = createSale("A6");
        Boolean expected = saleDao.create(sale);
        assertTrue(expected);

        Boolean expected1 = saleDao.create(sale);
        assertFalse(expected1);
        assertTrue(sale.getStatus().equals(Sale.Status.STARTED));
    }

    @Test
    public void removeProductQuantity() throws TypeKeyNotFoundException {
        String codeSale = "A7";
        Sale sale = createSale(codeSale);
        Boolean expected = saleDao.create(sale);
        assertTrue(expected);
        assertNotNull(sale);
        assertEquals(codeSale, sale.getCode());

        Product prod = createProduct(codeSale, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codeSale, prod.getIdCode());

        Sale foundSale = saleDao.find(codeSale);
        foundSale.addProductQuantity(prod, 1);
        assertTrue(sale.getQuantityTotalProducts() == 3);
        assertTrue(sale.getTotalValue().equals(BigDecimal.valueOf(70)));


        foundSale.removeProductQuantity(prod, 1);
        assertTrue(sale.getQuantityTotalProducts() == 2);
        assertTrue(sale.getTotalValue().equals(BigDecimal.valueOf(20)));
        assertTrue(sale.getStatus().equals(Sale.Status.STARTED));
    }

    @Test
    public void removesProductQuantityByOne() throws TypeKeyNotFoundException {
        String codeSale = "A8";
        Sale sale = createSale(codeSale);
        Boolean expected = saleDao.create(sale);
        assertTrue(expected);
        assertNotNull(sale);
        assertEquals(codeSale, sale.getCode());

        Product prod = createProduct(codeSale, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codeSale, prod.getIdCode());

        Sale foundSale = saleDao.find(codeSale);
        foundSale.addProductQuantity(prod, 1);
        assertTrue(sale.getQuantityTotalProducts() == 3);
        assertTrue(sale.getTotalValue().equals(BigDecimal.valueOf(70)));


        foundSale.removeProductQuantity(prod, 1);
        assertTrue(sale.getQuantityTotalProducts() == 2);
        assertTrue(sale.getTotalValue().equals(BigDecimal.valueOf(20)));
        assertTrue(sale.getStatus().equals(Sale.Status.STARTED));
    }

    @Test
    public void removeAllQuantity() throws TypeKeyNotFoundException {
        String codeSale = "A9";
        Sale sale = createSale(codeSale);
        Boolean expected = saleDao.create(sale);
        assertTrue(expected);
        assertNotNull(sale);
        assertEquals(codeSale, sale.getCode());

        Product prod = createProduct(codeSale, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codeSale, prod.getIdCode());

        Sale foundSale = saleDao.find(codeSale);
        foundSale.addProductQuantity(prod, 1);
        assertTrue(sale.getQuantityTotalProducts() == 3);
        assertTrue(sale.getTotalValue().equals(BigDecimal.valueOf(70)));


        foundSale.removeAllQuantity();
        assertTrue(sale.getQuantityTotalProducts() == 0);
        assertTrue(sale.getTotalValue().equals(BigDecimal.valueOf(0)));
        assertTrue(sale.getStatus().equals(Sale.Status.STARTED));
    }

    @Test
    public void completeSale() throws TypeKeyNotFoundException {
        String codeSale = "A10";
        Sale sale = createSale(codeSale);
        Boolean expected = saleDao.create(sale);
        assertTrue(expected);
        assertNotNull(sale);
        assertEquals(codeSale, sale.getCode());

        saleDao.completeSale(sale);

        Sale foundSale = saleDao.find(codeSale);
        assertEquals(sale.getCode(), foundSale.getCode());
        assertEquals(sale.getStatus(), foundSale.getStatus());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void doesNotAddProductsToCompleteSale() throws TypeKeyNotFoundException {
        String codeSale = "A11";
        Sale sale = createSale(codeSale);
        Boolean expected = saleDao.create(sale);
        assertTrue(expected);
        assertNotNull(sale);
        assertEquals(codeSale, sale.getCode());

        saleDao.completeSale(sale);
        Sale foundSale = saleDao.find(codeSale);
        assertEquals(sale.getCode(), foundSale.getCode());
        assertEquals(sale.getStatus(), foundSale.getStatus());

        foundSale.addProductQuantity(this.product, 1);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void doesNotDestroySale() throws TypeKeyNotFoundException {
        Sale sale = createSale("A12");
        saleDao.create(sale);
        saleDao.destroy(sale.getCode());
    }

    private Product createProduct(String idCode, BigDecimal value) throws TypeKeyNotFoundException {
        Product product = new Product("Bacon Strips", idCode, value.toString(), "Delicious bacon strips");
        productDao.create(product);
        return product;
    }

    private Consumer createConsumer() throws TypeKeyNotFoundException {
        Consumer consumer = new Consumer(
                "Rodrigo",
                "123123484",
                "1199999999",
                "End",
                "10",
                "São Paulo",
                "SP"
        );
        return consumer;
    }

    private Sale createSale(String code) {
        Sale sale = new Sale();
        sale.setCode(code);
        sale.setDate(Instant.now());
        sale.setConsumer(this.consumer);
        sale.setStatus(Sale.Status.STARTED);
        sale.addProductQuantity(this.product, 2);
        return sale;
    }
}