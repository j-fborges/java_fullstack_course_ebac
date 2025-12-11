package main.java.br.com.j_fborges.dao;

import main.java.br.com.j_fborges.domain.Product;

import java.util.List;

public interface IProductDAO {

    public Product register(Product product);

    public void delete(Product product);

    public Product find(Long productId);

    public Product findByProductCode(String productIdCode);

    public List<Product> findAll();

}
