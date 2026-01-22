package br.com.j_fborges.service;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.j_fborges.dao.IConsumerDAO;
import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.service.generic.GenericService;

@Stateless
public class ConsumerService extends GenericService<Consumer, Long> implements IConsumerService {
	
	private IConsumerDAO consumerDAO;
	
	@Inject
    public ConsumerService(IConsumerDAO dao) {
        super(dao);
        this.consumerDAO = dao;
    }

    @Override
    public Collection<Consumer> loadConsumers() {
        return dao.findAll();
    }
    
	@Override
	public List<Consumer> filterConsumers(String query) {
		return consumerDAO.filterConsumers(query);
	}
}
