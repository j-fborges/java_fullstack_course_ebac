package main.java;

import main.java.br.com.j_fborges.dao.ProductDAO;
import main.java.br.com.j_fborges.domain.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome, Lets register products.");
        System.out.println(Product.class);



        ProductDAO productDAO = new ProductDAO();


        Product product = new Product();

        product.setIdCode("H3H5K5");
        product.setCategory("Food");
        product.setPrice(BigDecimal.valueOf(55.22));
        product.setTitle("Soy Sauce");
        product.setDescription("You need this for your sushi");

        productDAO.register(product);

        List<Product> products = productDAO.findAll();

        for(Product p : products){
            System.out.println(p);
        }

        System.out.println(productDAO.find(product.getId()));
        System.out.println(productDAO.findByProductCode(product.getIdCode()));

        for(Product p : products){
            productDAO.delete(p);
        }
    }
}

