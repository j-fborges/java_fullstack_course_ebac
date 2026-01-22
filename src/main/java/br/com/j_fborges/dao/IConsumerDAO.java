package br.com.j_fborges.dao;

import java.util.List;

import br.com.j_fborges.dao.generic.IGenericDAO;
import br.com.j_fborges.domain.Consumer;


public interface IConsumerDAO extends IGenericDAO<Consumer, Long> {

    @Override
    Consumer find(Long value);
    
    List<Consumer> filterConsumers(String query);
}
