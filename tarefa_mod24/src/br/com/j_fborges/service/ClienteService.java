package br.com.j_fborges.service;

import br.com.j_fborges.dao.ClienteDao;
import br.com.j_fborges.dao.ClienteDaoMock;
import br.com.j_fborges.dao.IClienteDao;

/**
 *
 */
public class ClienteService {

    private IClienteDao clienteDao;

    public ClienteService(IClienteDao clienteDao) {
        //clienteDao = new ClienteDao();
        //clienteDao = new ClienteDaoMock();
        this.clienteDao = clienteDao;
    }

    public String salvar() {
        clienteDao.salvar();
        return "Sucesso";
    }
}
