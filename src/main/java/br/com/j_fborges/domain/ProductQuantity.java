package br.com.j_fborges.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_PRODUCT_QUANTITY")
public class ProductQuantity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_qnt_seq")
    @SequenceGenerator(name = "prod_qnt_seq", sequenceName = "sq_prod_qnt", initialValue = 1, allocationSize = 1)
    private Long id;

    public ProductQuantity(){

    }

    public ProductQuantity(Product product, Integer quantity) {
        this.quantity = quantity;
        this.product = product;
        this.totalValue = product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name = "TOTAL_VALUE", nullable = false)
    private BigDecimal totalValue;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_venda_fk", foreignKey = @ForeignKey(name = "fk_prod_qnt_sale"),
            referencedColumnName = "id", nullable = false)
    private Sale sale;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public void addQuantity(Integer quantity) {
        this.quantity += quantity;
        BigDecimal newValue = this.product.getPrice().multiply(BigDecimal.valueOf(quantity));
        BigDecimal novoTotal = this.totalValue.add(newValue);
        this.totalValue = novoTotal;
    }

    public void removeQuantity(Integer quantity) {
        this.quantity -= quantity;
        BigDecimal newValue = this.product.getPrice().multiply(BigDecimal.valueOf(quantity));
        this.totalValue = this.totalValue.subtract(newValue);
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
