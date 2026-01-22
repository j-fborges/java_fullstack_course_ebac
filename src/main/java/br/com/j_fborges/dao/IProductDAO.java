package br.com.j_fborges.dao;

import java.util.List;

import br.com.j_fborges.dao.generic.IGenericDAO;
import br.com.j_fborges.domain.Product;

public interface IProductDAO extends IGenericDAO<Product, Long> {
	
	List<Product> filterProducts(String query);
}
