/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.j_fborges.factory;

import br.com.j_fborges.domain.Persistent;
import br.com.j_fborges.domain.Product;
import br.com.j_fborges.exception.InvalidDataException;

/**
 *
 * @author root
 */
public class ProductFactory implements PersistentFactory {

    @Override
    public Persistent createObject(String parsedInputs[]) throws InvalidDataException {
        try {
            return new Product(parsedInputs[0], parsedInputs[1], parsedInputs[2], parsedInputs[3], parsedInputs[4]);
        } catch(IndexOutOfBoundsException e) {
            throw new InvalidDataException("Product Data are invalid");
        }
    }

    @Override
    public Persistent createEmptyObject() {
        return new Product();
    }
}
