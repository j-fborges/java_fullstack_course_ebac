package br.com.j_fborges.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.j_fborges.domain.Product;
import br.com.j_fborges.service.IProductService;


@Named("productController")
@ViewScoped
public class ProductController implements Serializable {
	
public static final long serialVersionUID = 367088063926303823L;
	
	private Product product;
	
	private Collection<Product> products;
	
	@Inject
	private IProductService productService;
	
	private Boolean isUpdate;
	
	@PostConstruct
    public void init() {
		try {
			this.isUpdate = false;
			this.product = new Product();
			this.products = productService.findAll();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error listing products"));
		}
	}
	
	public void cancel() {
		try {
			this.isUpdate = false;
			this.product = new Product();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error cancelling action"));
		}
		
    } 
	
	public void edit(Product product) {
		try {
			this.isUpdate = true;
			this.product = product;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error editing products"));
		}
		
    } 
	
	public void delete(Product product) {
		try {
			productService.delete(product);
			products.remove(product);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error deleting product"));
		}
		
    } 
	
	public void add() {
		try {
			productService.register(product);
			this.products = productService.findAll();
			this.product = new Product();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error registering product"));
		}
		
        
    }

    public void update() {
    	try {
    		productService.update(this.product);
			cancel();
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Product updated successfully"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error updating product"));
		}
        
    }
    
    public String returnToHomePage() {
		return "/index.xhtml"; 
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

}
