package br.com.j_fborges.converter;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import br.com.j_fborges.domain.Product;

@Named
@FacesConverter(value = "productConverter", forClass = Product.class)
@ApplicationScoped
public class ProductConverter implements Converter {
	
private static final String key = "br.com.j_fborges.converter.ProductConverter";
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value.isEmpty()) {
	        return null;
	    }
	    return getViewMap(context).get(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object product) {
		String id = ((Product)product).getId().toString();
		getViewMap(context).put(id, product);
	    return id;
	}
	
	private Map<String, Object> getViewMap(FacesContext context) {
	    Map<String, Object> viewMap = context.getViewRoot().getViewMap();
	    @SuppressWarnings({ "unchecked", "rawtypes" })
	    Map<String, Object> idMap = (Map) viewMap.get(key);
	    if (idMap == null) {
	        idMap = new HashMap<String, Object>();
	        viewMap.put(key, idMap);
	    }
	    return idMap;
	}

}
