package br.com.j_fborges.dao;

import br.com.j_fborges.dao.generic.GenericDAO;
import br.com.j_fborges.domain.Sale;
import br.com.j_fborges.exception.TypeKeyNotFoundException;

import java.math.BigDecimal;

public class SaleMapDAO extends GenericDAO<Sale, String> implements ISaleDao {


    @Override
    public void completeSale(Sale sale) throws TypeKeyNotFoundException {
        sale.setStatus(Sale.Status.COMPLETED);
        super.update(sale);
    }

    @Override
    public Class<Sale> getClassType() {
        return Sale.class;
    }

    @Override
    public void updateData(Sale sale, Sale saleRegistered) {
        saleRegistered.setCode(sale.getCode());
        saleRegistered.setStatus(sale.getStatus());
    }

    @Override
    public void destroy(String value) {
        throw new UnsupportedOperationException("OPERATION NOT PERMITTED");
    }
}
