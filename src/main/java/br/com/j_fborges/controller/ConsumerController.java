package br.com.j_fborges.controller;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.service.IConsumerService;
import br.com.j_fborges.utils.ReplaceUtils;


@Named
@ViewScoped
public class ConsumerController implements Serializable {
	
public static final long serialVersionUID = 8030245985235567808L;
	
	private Consumer consumer;
	
	private Collection<Consumer> consumers;
	
	@Inject
	private IConsumerService consumerService;
	
	private Boolean isUpdate;
	
	private String idNumberMask;
	
	private String telMask;
	
	@PostConstruct
    public void init() {
		try {
			this.isUpdate = false;
			this.consumer = new Consumer();
			this.consumers = consumerService.findAll();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error listing consumers"));
		}
	}
	
	public void cancel() {
		try {
			this.isUpdate = false;
			this.consumer = new Consumer();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error cancelling action"));
		}
		
    } 
	
	public void edit(Consumer consumer) {
		try {
			this.isUpdate = true;
			this.consumer = consumer;
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error deleting consumer"));
		}
		
    } 
	
	public void delete(Consumer consumer) {
		try {
			consumerService.delete(consumer);
			consumers.remove(consumer);
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error deleting consumer"));
		}
		
    } 
	
	public void add() {
		try {
			removeInvalidCharacters();
			cleanFields();
			consumerService.register(consumer);
			this.consumers = consumerService.findAll();
			this.consumer = new Consumer();
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error registering consumer"));
		}
		
        
    }

    public void removeInvalidCharacters() {
    	Long cpf = Long.valueOf(ReplaceUtils.replace(getIdNumberMask(), ".", "-"));
    	this.consumer.setIdNumber(cpf);
    	
    	Long tel = Long.valueOf(ReplaceUtils.replace(getTelMask(), "(", ")", " ", "-"));
    	this.consumer.setTel(tel);
	}
    
	public void cleanFields() {
    	setIdNumberMask(null);
    	setTelMask(null);
    }

	public void update() {
    	try {
    		removeInvalidCharacters();
			consumerService.update(this.consumer);
			cancel();
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Consumer update successfully"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage("Error while updating consumer"));
		}
        
    }
	
	public String returnToHomePage() {
		return "/index.xhtml"; 
	}

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	public Collection<Consumer> getConsumers() {
		return consumers;
	}

	public void setConsumers(Collection<Consumer> consumers) {
		this.consumers = consumers;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getIdNumberMask() {
		return idNumberMask;
	}

	public void setIdNumberMask(String idNumberMask) {
		this.idNumberMask = idNumberMask;
	}

	public String getTelMask() {
		return telMask;
	}

	public void setTelMask(String telMask) {
		this.telMask = telMask;
	}

}
