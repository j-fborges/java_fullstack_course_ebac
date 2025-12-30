package br.com.j_fborges.dao;

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
}
