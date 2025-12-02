package br.com.j_fborges.domain;

import java.math.BigDecimal;

public class Product implements Persistent{

    private Long id;

    private String idCode;

    private String title;

    private BigDecimal price;

    private String category;

    private String description;

    public Product(String id, String title, String idCode, String price, String category, String description){
        this.id = Long.valueOf(id);
        this.title = title;
        this.idCode = idCode;
        this.price = new BigDecimal(price);
        this.category = category;
        this.description = description;
    }

    public Product(){}

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

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
