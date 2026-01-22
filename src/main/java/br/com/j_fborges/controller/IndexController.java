package br.com.j_fborges.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;





@Named
@ViewScoped
public class IndexController implements Serializable {
	
	private static final long serialVersionUID = -784519597996507487L;

	public String redirectConsumer() {
		return "/consumer/list.xhtml";
	}
	
	public String redirectProduct() {
		return "/product/list.xhtml";
	}
	
	public String redirectSale() {
		return "/sale/list.xhtml";
	}
}
