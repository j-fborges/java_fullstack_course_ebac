package br.com.j_fborges.dao;

import br.com.j_fborges.dao.generic.GenericDAO;
import br.com.j_fborges.domain.Product;


public class ProductMapDAO extends GenericDAO<Product, String> implements IProductDAO {


    public ProductMapDAO() {
        super();
    }

    @Override
    public Class<Product> getClassType() {
        return Product.class;
    }

    @Override
    public void updateData(Product entity, Product entityRegistered) {
        entityRegistered.setIdCode(entity.getIdCode());
        entityRegistered.setDescription(entity.getDescription());
        entityRegistered.setTitle(entity.getTitle());
        entityRegistered.setValue(entity.getValue());
    }
}
