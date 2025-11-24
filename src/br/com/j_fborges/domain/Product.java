package br.com.j_fborges.domain;

import br.com.j_fborges.annotation.TypeIDKey;

import java.math.BigDecimal;

public class Product implements Persistent{

    @TypeIDKey("getIdCode")
    private String idCode;

    private String title;

    private String description;

    private BigDecimal value;
    
    public Product(String title, String idCode, String value, String description){
        this.title = title;
        this.idCode = idCode;
        this.value = new BigDecimal(value);
        this.description = description;
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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
