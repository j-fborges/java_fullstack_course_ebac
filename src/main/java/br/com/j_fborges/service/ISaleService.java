package br.com.j_fborges.service;

import br.com.j_fborges.domain.Sale;
import br.com.j_fborges.exception.DAOException;
import br.com.j_fborges.exception.TypeKeyNotFoundException;
import br.com.j_fborges.service.generic.IGenericService;

public interface ISaleService extends IGenericService<Sale, Long> {
	
	public void completeSale(Sale sale) throws TypeKeyNotFoundException, DAOException;
	
	public void cancelSale(Sale sale) throws TypeKeyNotFoundException, DAOException;

	Sale findWithCollections(Long id);
	
}
