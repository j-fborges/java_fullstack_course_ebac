package br.com.j_fborges.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.j_fborges.domain.Consumer;

@Repository
public interface IConsumerRepository extends CrudRepository<Consumer, Long> {
	
}
