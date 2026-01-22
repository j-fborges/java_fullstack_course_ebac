package br.com.j_fborges.service;

import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.j_fborges.dao.ISaleDAO;
import br.com.j_fborges.domain.Sale;
import br.com.j_fborges.domain.Sale.Status;
import br.com.j_fborges.exception.DAOException;
import br.com.j_fborges.exception.TypeKeyNotFoundException;
import br.com.j_fborges.service.generic.GenericService;


@Stateless
public class SaleService extends GenericService<Sale, Long> implements ISaleService {
	
	ISaleDAO dao;
	
	@Inject
	public SaleService(ISaleDAO dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public void completeSale(Sale sale) throws TypeKeyNotFoundException, DAOException {
		sale.setStatus(Status.COMPLETED);
		dao.completeSale(sale);
	}

	@Override
	public void cancelSale(Sale sale) throws TypeKeyNotFoundException, DAOException {
		sale.setStatus(Status.CANCELLED);
		dao.cancelSale(sale);
	}

	@Override
	public Sale findWithCollections(Long id) {
		return dao.findWithCollections(id);
	}

	@Override
	public Sale register(Sale entity) throws SQLException {
		entity.setStatus(Status.STARTED);
		return super.register(entity);
	}
}
