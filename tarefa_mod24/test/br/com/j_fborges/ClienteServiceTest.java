package br.com.j_fborges;

import br.com.j_fborges.dao.ClienteDao;
import br.com.j_fborges.dao.ClienteDaoMock;
import br.com.j_fborges.dao.IClienteDao;
import br.com.j_fborges.service.ClienteService;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class ClienteServiceTest {

    @Test
    public void salvarTest() {
        IClienteDao mockDao = new ClienteDaoMock();
        ClienteService service = new ClienteService(mockDao);
        String retorno = service.salvar();
        Assert.assertEquals("Sucesso", retorno);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void esperadoErroNoSalvarTest() {
        IClienteDao mockDao = new ClienteDao();
        ClienteService service = new ClienteService(mockDao);
        String retorno = service.salvar();
        Assert.assertEquals("Sucesso", retorno);
    }
}
