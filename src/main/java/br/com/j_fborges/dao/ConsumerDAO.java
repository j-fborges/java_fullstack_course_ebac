package br.com.j_fborges.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.j_fborges.dao.generic.GenericDAO;
import br.com.j_fborges.domain.Consumer;

public class ConsumerDAO extends GenericDAO<Consumer, Long> implements IConsumerDAO {

    public ConsumerDAO(){
        super(Consumer.class);
    }

    @Override
    public Class<Consumer> getClassType() {
        return Consumer.class;
    }
    
    @Override
	public List<Consumer> filterConsumers(String query) {
		TypedQuery<Consumer> tpQuery = 
				this.entityManager.createNamedQuery("Consumer.findByName", this.persistentClass);
		tpQuery.setParameter("nome", "%" + query + "%");
        return tpQuery.getResultList();
		
	}
}
