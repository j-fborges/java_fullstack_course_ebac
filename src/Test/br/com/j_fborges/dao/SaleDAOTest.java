package br.com.j_fborges.dao;

import br.com.j_fborges.dao.mocks.SaleDaoMock;
import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.domain.Product;
import br.com.j_fborges.domain.Sale;
import br.com.j_fborges.exception.DAOException;
import br.com.j_fborges.exception.NonUniqueEntryException;
import br.com.j_fborges.exception.TableException;
import br.com.j_fborges.exception.TypeKeyNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Collection;
import java.util.Random;

import static org.junit.Assert.*;

public class SaleDAOTest {


    private ISaleDao saleDao;

    private ISaleDao saleDeletionDao;

    private IConsumerDAO consumerDao;

    private IProductDAO productDao;

    private Random rd;

    private Consumer consumer;

    private Product product;

    public SaleDAOTest() {
        this.saleDao = new SaleDAO();
        saleDeletionDao = new SaleDaoMock();
        this.consumerDao = new ConsumerDAO();
        this.productDao = new ProductDAO();
        rd = new Random();
    }

    @Before
    public void init() throws TableException, DAOException, TypeKeyNotFoundException {
        this.consumer = createConsumer();
        this.product = createProduct("A1", BigDecimal.TEN);
    }

    @After
    public void end() throws DAOException {
        destroySales();
        destroyProducts();
        consumerDao.destroy(this.consumer);
    }

    @Test
    public void pesquisar() throws TypeKeyNotFoundException, NonUniqueEntryException, TableException, DAOException {
        Sale sale = createSale("A1");
        Sale savedSale = saleDao.create(sale);
        assertNotNull(savedSale);
        Sale foundSale = saleDao.find(sale.getId());
        assertNotNull(foundSale);
        assertEquals(sale.getCode(), foundSale.getCode());
    }

    @Test
    public void salvar() throws TypeKeyNotFoundException, DAOException, NonUniqueEntryException, TableException {
        Sale sale = createSale("A2");
        Sale savedSale = saleDao.create(sale);
        assertNotNull(savedSale);

        assertTrue(sale.getTotalValue().equals(BigDecimal.valueOf(20)));
        assertTrue(sale.getStatus().equals(Sale.Status.STARTED));

        Sale foundSale = saleDao.find(sale.getId());
        assertTrue(foundSale.getId() != null);
        assertEquals(sale.getCode(), foundSale.getCode());
    }

    @Test
    public void cancelSale() throws TypeKeyNotFoundException, NonUniqueEntryException, TableException, DAOException {
        String codigoVenda = "A3";
        Sale sale = createSale(codigoVenda);
        Sale savedSale = saleDao.create(sale);
        assertNotNull(savedSale);
        assertNotNull(sale);
        assertEquals(codigoVenda, sale.getCode());

        savedSale.setStatus(Sale.Status.CANCELLED);
        saleDao.cancelSale(sale);

        Sale foundSale = saleDao.find(sale.getId());
        assertEquals(codigoVenda, foundSale.getCode());
        assertEquals(Sale.Status.CANCELLED, foundSale.getStatus());
    }

    @Test
    public void adicionarMaisProductsDoMesmo() throws TypeKeyNotFoundException, NonUniqueEntryException, TableException, DAOException {
        String codigoVenda = "A4";
        Sale sale = createSale(codigoVenda);
        Sale savedSale = saleDao.create(sale);
        assertNotNull(savedSale);
        assertNotNull(sale);
        assertEquals(codigoVenda, sale.getCode());

        Sale foundSale = saleDao.findWithCollections(sale.getId());
        foundSale.addProductQuantity(product, 1);

        assertTrue(foundSale.getQuantityTotalProducts() == 3);
        BigDecimal valorTotal = BigDecimal.valueOf(30).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(foundSale.getTotalValue().equals(valorTotal));
        assertTrue(foundSale.getStatus().equals(Sale.Status.STARTED));
    }

    @Test
    public void adicionarMaisProductsDiferentes() throws TypeKeyNotFoundException, NonUniqueEntryException, TableException, DAOException {
        String codigoVenda = "A5";
        Sale sale = createSale(codigoVenda);
        Sale savedSale = saleDao.create(sale);
        assertNotNull(savedSale);
        assertNotNull(sale);
        assertEquals(codigoVenda, sale.getCode());

        Product prod = createProduct(codigoVenda, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codigoVenda, prod.getIdCode());

        //TODO Usando este método apra evitar a exception org.hibernate.LazyInitializationException
        // Ele busca todos os dados da lista pois a mesma por default é lazy
        Sale foundSale = saleDao.findWithCollections(sale.getId());
        foundSale.addProductQuantity(prod, 1);

        assertTrue(foundSale.getQuantityTotalProducts() == 3);
        BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(foundSale.getTotalValue().equals(valorTotal));
        assertTrue(foundSale.getStatus().equals(Sale.Status.STARTED));
    }

    @Test(expected = DAOException.class)
    public void salvarVendaMesmoCodigoExistente() throws TypeKeyNotFoundException, DAOException {
        Sale sale = createSale("A6");
        Sale savedSale = saleDao.create(sale);
        assertNotNull(savedSale);

        Sale sale1 = createSale("A6");
        Sale savedSale1 = saleDao.create(sale1);
        assertNull(savedSale1);
        assertTrue(sale.getStatus().equals(Sale.Status.STARTED));
    }

    @Test
    public void removeProductQuantity() throws TypeKeyNotFoundException, NonUniqueEntryException, TableException, DAOException {
        String codigoVenda = "A7";
        Sale sale = createSale(codigoVenda);
        Sale savedSale = saleDao.create(sale);
        assertNotNull(savedSale);
        assertNotNull(sale);
        assertEquals(codigoVenda, sale.getCode());

        Product prod = createProduct(codigoVenda, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codigoVenda, prod.getIdCode());

        Sale foundSale = saleDao.findWithCollections(sale.getId());
        foundSale.addProductQuantity(prod, 1);
        assertTrue(foundSale.getQuantityTotalProducts() == 3);
        BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(foundSale.getTotalValue().equals(valorTotal));


        foundSale.removeProductQuantity(prod, 1);
        assertTrue(foundSale.getQuantityTotalProducts() == 2);
        valorTotal = BigDecimal.valueOf(20).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(foundSale.getTotalValue().equals(valorTotal));
        assertTrue(foundSale.getStatus().equals(Sale.Status.STARTED));
    }

    @Test
    public void removerApenasUmProduto() throws TypeKeyNotFoundException, NonUniqueEntryException, TableException, DAOException {
        String codigoVenda = "A8";
        Sale sale = createSale(codigoVenda);
        Sale savedSale = saleDao.create(sale);
        assertNotNull(savedSale);
        assertNotNull(sale);
        assertEquals(codigoVenda, sale.getCode());

        Product prod = createProduct(codigoVenda, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codigoVenda, prod.getIdCode());

        Sale foundSale = saleDao.findWithCollections(sale.getId());
        foundSale.addProductQuantity(prod, 1);
        assertTrue(foundSale.getQuantityTotalProducts() == 3);
        BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(foundSale.getTotalValue().equals(valorTotal));


        foundSale.removeProductQuantity(prod, 1);
        assertTrue(foundSale.getQuantityTotalProducts() == 2);
        valorTotal = BigDecimal.valueOf(20).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(foundSale.getTotalValue().equals(valorTotal));
        assertTrue(foundSale.getStatus().equals(Sale.Status.STARTED));
    }

    @Test
    public void removeAllQuantity() throws NonUniqueEntryException, TableException, DAOException {
        String codigoVenda = "A9";
        Sale sale = createSale(codigoVenda);
        Sale savedSale = saleDao.create(sale);
        assertNotNull(savedSale);
        assertNotNull(sale);
        assertEquals(codigoVenda, sale.getCode());

        Product prod = createProduct(codigoVenda, BigDecimal.valueOf(50));
        assertNotNull(prod);
        assertEquals(codigoVenda, prod.getIdCode());

        Sale foundSale = saleDao.findWithCollections(sale.getId());
        foundSale.addProductQuantity(prod, 1);
        assertTrue(foundSale.getQuantityTotalProducts() == 3);
        BigDecimal valorTotal = BigDecimal.valueOf(70).setScale(2, RoundingMode.HALF_DOWN);
        assertTrue(foundSale.getTotalValue().equals(valorTotal));


        foundSale.removeAllQuantity();
        assertTrue(foundSale.getQuantityTotalProducts() == 0);
        assertTrue(foundSale.getTotalValue().equals(BigDecimal.valueOf(0)));
        assertTrue(foundSale.getStatus().equals(Sale.Status.STARTED));
    }

    @Test
    public void completeSale() throws NonUniqueEntryException, TableException, DAOException, TypeKeyNotFoundException {
        String codigoVenda = "A10";
        Sale sale = createSale(codigoVenda);
        Sale savedSale = saleDao.create(sale);
        assertNotNull(savedSale);
        assertNotNull(sale);
        assertEquals(codigoVenda, sale.getCode());

        saleDao.completeSale(sale);

        Sale foundSale = saleDao.findWithCollections(sale.getId());
        assertEquals(sale.getCode(), foundSale.getCode());
        assertEquals(Sale.Status.COMPLETED, foundSale.getStatus());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void attemptsToAddProductsToCompletedSale() throws NonUniqueEntryException, TableException, DAOException, TypeKeyNotFoundException {
        String codigoVenda = "A11";
        Sale sale = createSale(codigoVenda);
        Sale savedSale = saleDao.create(sale);
        assertNotNull(savedSale);
        assertNotNull(sale);
        assertEquals(codigoVenda, sale.getCode());

        sale.setStatus(Sale.Status.COMPLETED);
        saleDao.completeSale(sale);

        Sale foundSale = saleDao.findWithCollections(sale.getId());
        assertEquals(sale.getCode(), foundSale.getCode());
        assertEquals(Sale.Status.COMPLETED, foundSale.getStatus());

        foundSale.addProductQuantity(this.product, 1);

    }


    private void destroyProducts() throws DAOException {
        Collection<Product> list = this.productDao.findAll();
        list.forEach(prod -> {
            try {
                this.productDao.destroy(prod);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    private void destroySales() throws DAOException {
        Collection<Sale> list = this.saleDeletionDao.findAll();
        list.forEach(prod -> {
            try {
                this.saleDeletionDao.destroy(prod);
            } catch (DAOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

    private Product createProduct(String codigo, BigDecimal valor) throws NonUniqueEntryException, TableException, DAOException {
        Product product = new Product();
        product.setIdCode(codigo);
        product.setDescription("Produto 1");
        product.setTitle("Produto 1");
        product.setPrice(valor);
        productDao.create(product);
        return product;
    }

    private Consumer createConsumer() throws DAOException {
        Consumer consumer = new Consumer();
        consumer.setIdNumber(rd.nextLong());
        consumer.setName("Rodrigo");
        consumer.setCity("São Paulo");
        consumer.setEmail("bar@bar.foo");
        consumer.setAddress("End");
        consumer.setState("SP");
        consumer.setAddressNumber(10);
        consumer.setTel(1199999999L);
        consumerDao.create(consumer);
        return consumer;
    }

    private Sale createSale(String codigo) {
        Sale sale = new Sale();
        sale.setCode(codigo);
        sale.setDate(Instant.now());
        sale.setConsumer(this.consumer);
        sale.setStatus(Sale.Status.STARTED);
        sale.addProductQuantity(this.product, 2);
        return sale;
    }

}