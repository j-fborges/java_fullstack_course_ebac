package br.com.j_fborges;

import br.com.j_fborges.dao.ConsumerDAOTest;
import br.com.j_fborges.dao.ProductDAOTest;
import br.com.j_fborges.service.ConsumerServiceTest;
import br.com.j_fborges.service.ProductServiceTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ ConsumerServiceTest.class, ConsumerDAOTest.class,
        ProductServiceTest.class, ProductDAOTest.class})
public class AllTests {

}
