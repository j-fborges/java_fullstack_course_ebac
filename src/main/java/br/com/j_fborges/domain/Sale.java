package br.com.j_fborges.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "TB_SALE")
public class Sale implements Persistent {

    public enum Status {
        STARTED, COMPLETED, CANCELLED;

        public static Status getByName(String value) {
            for (Status status : Status.values()) {
                if (status.name().equals(value)) {
                    return status;
                }
            }
            return null;
        }
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda_seq")
    @SequenceGenerator(name = "venda_seq", sequenceName = "sq_venda", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "CODE", nullable = false, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(name = "id_consumer_fk",
    foreignKey = @ForeignKey(name = "fk_sale_consumer"), referencedColumnName = "id", nullable = false)
    private Consumer consumer;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private Set<ProductQuantity> saleProducts;

    @Column(name = "TOTAL_VALUE", nullable = false)
    private BigDecimal totalValue;

    @Column(name = "DATE", nullable = false)
    private Instant date;

    @Enumerated(EnumType.STRING)
    @Column(name = "SALE_STATUS", nullable = false)
    private Status status;

    public Sale() {
        saleProducts = new HashSet<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        validateStatus();
        this.code = code;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        validateStatus();
        this.consumer = consumer;
    }

    public Set<ProductQuantity> getProducts() {
        return saleProducts;
    }

    public void setProducts(Set<ProductQuantity> products) {
        validateStatus();
        this.saleProducts = products;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        validateStatus();
        this.totalValue = totalValue;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        validateStatus();
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        validateStatus();
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

            ProductQuantity prod = new ProductQuantity(product, quantity);
            prod.setSale(this);
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
