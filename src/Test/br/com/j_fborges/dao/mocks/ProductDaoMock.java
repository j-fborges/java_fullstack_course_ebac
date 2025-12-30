package br.com.j_fborges.dao.mocks;

import br.com.j_fborges.dao.IProductDAO;
import br.com.j_fborges.domain.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class ProductDaoMock implements IProductDAO {

    @Override
    public Product create(Product entity) {
        return entity;
    }

    @Override
    public void destroy(Product product) {

    }

    @Override
    public Product update(Product entity) {

        return null;
    }

    @Override
    public Product find(Long id) {
        return new Product(id.toString(), "Foo", "fygjyg86486", "10.02", "Bar", "BarFoo");
    }

    @Override
    public Collection<Product> findAll() {
        return List.of();
    }
}
