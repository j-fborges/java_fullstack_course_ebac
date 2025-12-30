package br.com.j_fborges.dao.mocks;


import br.com.j_fborges.dao.ISaleDao;
import br.com.j_fborges.dao.generic.GenericDAO;
import br.com.j_fborges.domain.Sale;

import java.sql.SQLException;

public class SaleDaoMock extends GenericDAO<Sale, Long> implements ISaleDao {

    public SaleDaoMock() {
        super(Sale.class);
    }


    @Override
    public void completeSale(Sale sale) {
        throw new UnsupportedOperationException("OPERATION NOT PERMITTED");
    }

    @Override
    public void cancelSale(Sale venda) {
        throw new UnsupportedOperationException("OPERATION NOT PERMITTED");
    }

    @Override
    public Sale findWithCollections(Long id) {
        throw new UnsupportedOperationException("OPERATION NOT PERMITTED");
    }

    @Override
    public Class<Sale> getClassType() {
        return null;
    }

    @Override
    public Sale find(Long value) {
        return null;
    }
}