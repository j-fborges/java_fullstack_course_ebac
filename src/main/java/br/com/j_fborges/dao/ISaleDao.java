package br.com.j_fborges.dao;

import br.com.j_fborges.dao.generic.IGenericDAO;
import br.com.j_fborges.domain.Sale;
import br.com.j_fborges.exception.TypeKeyNotFoundException;

public interface ISaleDao extends IGenericDAO<Sale, Long> {

    public void completeSale(Sale sale) throws TypeKeyNotFoundException;

    public void cancelSale(Sale sale) throws TypeKeyNotFoundException;

    public Sale findWithCollections(Long id);
}
