package br.com.j_fborges.domain;

import br.com.j_fborges.annotation.MatchingDAOClass;
import br.com.j_fborges.annotation.MatchingPersistentFactory;

import java.math.BigDecimal;

@MatchingPersistentFactory("br.com.j_fborges.factory.ProductFactory")
@MatchingDAOClass("br.com.j_fborges.dao.ProductDAO")
public class Product implements Persistent{

    private Long id;

    private String idCode;

    private String title;

    private String description;

    private BigDecimal price;
    
    public Product(String id, String title, String idCode, String price, String description){
        this.id = Long.valueOf(id);
        this.title = title;
        this.idCode = idCode;
        this.price = new BigDecimal(price);
        this.description = description;
    }

    public Product(){}

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
