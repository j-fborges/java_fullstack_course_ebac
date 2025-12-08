package main.java.br.com.j_fborges.domain;

import javax.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "TST_PRODUCTS")
public class Product implements Persistent{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_seq")
    @SequenceGenerator(name = "products_seq", sequenceName = "sq_product", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "ID_CODE", length = 10, nullable = false, unique = true)
    private String idCode;

    @Column(name = "TITLE", length = 50, nullable = false)
    private String title;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "CATEGORY", length = 100)
    private String category;

    @Column(name = "DESCRIPTION", length = 300)
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", idCode='" + idCode + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
