package br.com.j_fborges.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Sale implements Persistent {

    public enum Status {
        STARTED, COMPLETED, CANCELLED;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    private Long id;

    private String code;

    private Consumer consumer;

    private Set<ProductQuantity> saleProducts;

    private BigDecimal totalValue;

    private Instant date;

    private Status status;

    public Sale() {
        saleProducts = new HashSet<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public Set<ProductQuantity> getProducts() {
        return saleProducts;
    }

    public void setProducts(Set<ProductQuantity> products) {
        this.saleProducts = products;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void addProductQuantity(Product product, Integer quantity) {
        validateStatus();
        Optional<ProductQuantity> op =
                saleProducts.stream().filter(filter -> filter.getProduct().getIdCode().equals(product.getIdCode())).findAny();
        if (op.isPresent()) {
            ProductQuantity produtpQtd = op.get();
            produtpQtd.addQuantity(quantity);
        } else {
            // Criar fabrica para criar ProductQuantity
            ProductQuantity prod = new ProductQuantity(product, quantity);
            saleProducts.add(prod);
        }
        recalculateSaleTotalValue();
    }

    private void validateStatus() {
        if (this.status == Status.COMPLETED) {
            throw new UnsupportedOperationException("IMPOSSIBLE TO EDIT COMPLETED SALE");
        }
    }

    public void removeProductQuantity(Product product, Integer quantity) {
        validateStatus();
        Optional<ProductQuantity> op =
                saleProducts.stream().filter(filter -> filter.getProduct().getIdCode().equals(product.getIdCode())).findAny();

        if (op.isPresent()) {
            ProductQuantity produtpQtd = op.get();
            if (produtpQtd.getQuantity()>quantity) {
                produtpQtd.removeQuantity(quantity);
                recalculateSaleTotalValue();
            } else {
                saleProducts.remove(op.get());
                recalculateSaleTotalValue();
            }

        }
    }

    public void removeAllQuantity() {
        validateStatus();
        saleProducts.clear();
        totalValue = BigDecimal.ZERO;
    }

    public Integer getQuantityTotalProducts() {
        int result = saleProducts.stream()
                .reduce(0, (partialCountResult, prod) -> partialCountResult + prod.getQuantity(), Integer::sum);
        return result;
    }

    private void recalculateSaleTotalValue() {
        validateStatus();
        BigDecimal totalValue = BigDecimal.ZERO;
        for (ProductQuantity prod : this.saleProducts) {
            totalValue = totalValue.add(prod.getTotalValue());
        }
        this.totalValue = totalValue;
    }

}
