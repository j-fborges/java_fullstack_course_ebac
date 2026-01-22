package br.com.j_fborges.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.RowEditEvent;

import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.domain.Product;
import br.com.j_fborges.domain.ProductQuantity;
import br.com.j_fborges.domain.Sale;
import br.com.j_fborges.service.IConsumerService;
import br.com.j_fborges.service.IProductService;
import br.com.j_fborges.service.ISaleService;


@Named
@ViewScoped
public class SaleController implements Serializable {
	
	private static final long serialVersionUID = -3508753726177740824L;
	
	private Sale sale;
	
	private Collection<Sale> sales;
	
	@Inject
	private ISaleService saleService;
	
	@Inject
	private IConsumerService consumerService;
	
	@Inject
	private IProductService productService;
	
	private Boolean isUpdate;
	
	private LocalDate dateSale;
	
	private Integer productQuantity;
	
	private Set<ProductQuantity> products;
	
	private Product selectedProduct;
	
	private BigDecimal totalValue; 
	
	@PostConstruct
    public void init() {
		try {
			this.isUpdate = false;
			this.sale = new Sale();
			this.products = new HashSet<>();
			this.sales = saleService.findAll();
			this.totalValue = BigDecimal.ZERO;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error listing sales"));
		}
	}
	
	public void cancel() {
		try {
			this.isUpdate = false;
			this.sale = new Sale();
			this.products = new HashSet<>();
			this.totalValue = BigDecimal.ZERO;
			this.dateSale = null;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error cancelling action"));
		}
		
    } 
	
	public void edit(Sale sale) {
		try {
			this.isUpdate = true;
			this.sale = this.saleService.findWithCollections(sale.getId());
			this.dateSale = LocalDate.ofInstant(this.sale.getDate(), ZoneId.systemDefault());
			this.products = this.sale.getProducts();
			this.sale.recalculateSaleTotalValue();
			this.totalValue = this.sale.getTotalValue();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error editing sale"));
		}
		
    } 
	
	public void delete(Sale sale) {
		try {
			saleService.cancelSale(sale);
			cancel();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error cancelling sale"));
		}
		
    } 
	
	public void finalizar(Sale sale) {
		try {
			saleService.completeSale(sale);
			cancel();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error completing sale"));
		}
		
    } 
	
	public void add() {
		try {
			sale.setDate(dateSale.atStartOfDay(ZoneId.systemDefault()).toInstant());
			saleService.register(sale);
			this.sales = saleService.findAll();
			cancel();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error registering sale"));
		}
    }
	
	public void update() {
    	try {
    		saleService.update(this.sale);
    		this.sales = saleService.findAll();
			cancel();
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Sale updated"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Errr updating sale"));
		}
        
    }
	
	public void addProduct() {
		Optional<ProductQuantity> prodOp = 
				this.sale.getProducts().stream().filter(prodF -> prodF.getProduct().getIdCode().equals(this.selectedProduct.getIdCode())).findFirst();

		if (prodOp.isPresent()) {
			ProductQuantity prod = prodOp.get();
			prod.addQuantity(this.productQuantity);
		} else {
			ProductQuantity prod = new ProductQuantity();
			prod.setProduct(this.selectedProduct);
			prod.addQuantity(this.productQuantity);
			prod.setSale(this.sale);
			this.sale.getProducts().add(prod);
		}
		this.sale.recalculateSaleTotalValue();
		this.products = this.sale.getProducts();
		this.totalValue = this.sale.getTotalValue();
	}
	
	public void removeProduct() {
		Optional<ProductQuantity> prodOp = 
				this.sale.getProducts().stream().filter(prodF -> prodF.getProduct().getIdCode().equals(this.selectedProduct.getIdCode())).findFirst();

		if (prodOp.isPresent()) {
			ProductQuantity prod = prodOp.get();
			prod.removeQuantity(this.productQuantity);
			if (prod.getQuantity() == 0 || prod.getQuantity() < 0) {
				this.sale.getProducts().remove(prod);
			}
			this.sale.recalculateSaleTotalValue();
			this.products = this.sale.getProducts();
			this.totalValue = this.sale.getTotalValue();
		}
		
	}
	
	public void removeProduct(ProductQuantity product) {
		
		this.sale.getProducts().remove(product);
		this.sale.recalculateSaleTotalValue();
		this.products = this.sale.getProducts();
		this.totalValue = this.sale.getTotalValue();
	}
	
	public void onRowEdit(RowEditEvent event) {
		ProductQuantity prod = (ProductQuantity) event.getObject();
		addOrRemoveProduct(prod);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(((ProductQuantity) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void addOrRemoveProduct(ProductQuantity prod) {
    	if (prod.getQuantity() != this.productQuantity) {
    		int quantidade =  this.productQuantity - prod.getQuantity();
    		if (quantidade > 0) {
    			prod.addQuantity(productQuantity);
    		} else {
    			this.products.remove(prod);
    		}
    		this.totalValue = BigDecimal.ZERO;
    		this.products.forEach(pro -> {
    			this.totalValue = this.totalValue.add(pro.getTotalValue());
    		});
    	}
    }
	
	public List<Consumer> filterConsumers(String query) {
		return this.consumerService.filterConsumers(query);
	}
	
	public List<Product> filterProducts(String query) {
		return this.productService.filterProducts(query);
	}
    
    public String returnToHomePage() {
		return "/index.xhtml"; 
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public Collection<Sale> getSales() {
		return sales;
	}

	public void setSales(Collection<Sale> sales) {
		this.sales = sales;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public LocalDate getSaleDate() {
		return dateSale;
	}

	public void setSaleDate(LocalDate dateSale) {
		this.dateSale = dateSale;
	}

	public Set<ProductQuantity> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductQuantity> products) {
		this.products = products;
	}

	public Integer getQuantityProduct() {
		return productQuantity;
	}

	public void setQuantityProduct(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}
}
