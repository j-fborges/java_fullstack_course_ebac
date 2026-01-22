package br.com.j_fborges.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.com.j_fborges.dao.generic.GenericDAO;
import br.com.j_fborges.domain.Product;

public class ProductDAO extends GenericDAO<Product, Long> implements IProductDAO {


    public ProductDAO() {
        super(Product.class);
    }

    @Override
    public Class<Product> getClassType() {
        return Product.class;
    }
    
    @Override
	public List<Product> filterProducts(String query) {
		TypedQuery<Product> tpQuery = 
				this.entityManager.createNamedQuery("Product.findByName", this.persistentClass);
		tpQuery.setParameter("nome", "%" + query + "%");
        return tpQuery.getResultList();
	}
}
