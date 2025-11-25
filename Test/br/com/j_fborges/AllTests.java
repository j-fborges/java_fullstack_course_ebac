package br.com.j_fborges;

import br.com.j_fborges.dao.ConsumerMapDAOTest;
import br.com.j_fborges.dao.ProductMapDAOTest;
import br.com.j_fborges.dao.SaleMapDAOTest;
import br.com.j_fborges.service.ConsumerServiceTest;
import br.com.j_fborges.service.ProductServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ConsumerServiceTest.class, ConsumerMapDAOTest.class,
        ProductServiceTest.class, ProductMapDAOTest.class,
        SaleMapDAOTest.class})
public class AllTests {

}
