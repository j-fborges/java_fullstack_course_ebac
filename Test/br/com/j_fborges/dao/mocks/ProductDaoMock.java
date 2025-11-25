package br.com.j_fborges.dao.mocks;

import br.com.j_fborges.dao.IProductDAO;
import br.com.j_fborges.domain.Product;
import br.com.j_fborges.exception.TypeKeyNotFoundException;

import java.util.Collection;
import java.util.List;

public class ProductDaoMock implements IProductDAO {
    @Override
    public Boolean create(Product entity) throws TypeKeyNotFoundException {
        return true;
    }

    @Override
    public void destroy(String value) {

    }

    @Override
    public void update(Product entity) throws TypeKeyNotFoundException {

    }

    @Override
    public Product find(String value) {
        return new Product("Foo", value, "10.02", "BarFoo");
    }

    @Override
    public Collection<Product> findAll() {
        return List.of();
    }
}
