package br.com.j_fborges.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_PRODUCT")
public class Product implements Persistent{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "sq_product", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "ID_CODE", nullable = false, unique = true)
    private String idCode;

    @Column(name = "TITLE", nullable = false, length = 50)
    private String title;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "CATEGORY", length = 50)
    private String category;

    @Column(name = "DESCRIPTION")
    private String description;

    public Product(String id, String title, String idCode, String price, String category, String description){
        this.id = Long.valueOf(id);
        this.title = title;
        this.idCode = idCode;
        this.price = new BigDecimal(price);
        this.category = category;
        this.description = description;
    }

    public Product(String title, String idCode, String price, String category, String description){
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
