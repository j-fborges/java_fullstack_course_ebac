/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.j_fborges.service;

import br.com.j_fborges.dao.IConsumerDAO;
import br.com.j_fborges.dao.IProductDAO;
import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.domain.Product;
import br.com.j_fborges.service.generic.GenericService;

/**
 *
 * @author root
 */
public class ProductService extends GenericService<Product, String> implements IProductService{
    
    public ProductService(IProductDAO dao) {
        super(dao);
    }

    @Override
    public Product findByIdCode(String idCode) {
        return this.dao.find(idCode);
    }
    
}
