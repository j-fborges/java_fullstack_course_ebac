/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.j_fborges.service;

import br.com.j_fborges.domain.Product;
import br.com.j_fborges.service.generic.IGenericService;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author root
 */

public interface IProductService extends IGenericService<Product, Long>{
    
    Product register(Product product) throws SQLException;

    void delete(Product product);

    Product update(Product product);

    public Collection<Product> loadProducts();

	List<Product> filterProducts(String query);
}
