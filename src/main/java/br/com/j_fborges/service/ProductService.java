/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.j_fborges.service;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.j_fborges.dao.IProductDAO;
import br.com.j_fborges.domain.Product;
import br.com.j_fborges.service.generic.GenericService;


/**
 *
 * @author root
 */

@Stateless
public class ProductService extends GenericService<Product, Long> implements IProductService{
    
	private IProductDAO productDAO;
	
	@Inject
    public ProductService(IProductDAO dao) {
        super(dao);
        this.productDAO = dao;
    }

    @Override
    public Collection<Product> loadProducts() {
        return dao.findAll();
    }
    
    @Override
	public List<Product> filterProducts(String query) {
		return productDAO.filterProducts(query);
	}

}
