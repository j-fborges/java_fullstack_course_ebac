/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.j_fborges.service;

import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.domain.Product;
import br.com.j_fborges.exception.TypeKeyNotFoundException;
import br.com.j_fborges.service.generic.IGenericService;

/**
 *
 * @author root
 */
public interface IProductService extends IGenericService<Product, String>{
    
    Boolean register(Product product) throws TypeKeyNotFoundException;

    Product findByIdCode(String idCode);

    void delete(String idCode);

    void update(Product product) throws TypeKeyNotFoundException;
}
