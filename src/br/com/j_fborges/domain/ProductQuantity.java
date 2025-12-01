package br.com.j_fborges.domain;

import java.math.BigDecimal;

public class ProductQuantity {
    private Product product;

    private Integer quantity;

    private BigDecimal totalValue;

    public ProductQuantity(Product product, Integer quantity) {
        this.quantity = quantity;
        this.product = product;
        this.totalValue = product.getPrice().multiply(BigDecimal.valueOf(quantity));
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
}
