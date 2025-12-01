package br.com.j_fborges.dao;

import br.com.j_fborges.dao.generic.GenericDAO;
import br.com.j_fborges.domain.Sale;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleDAO extends GenericDAO<Sale, String> implements ISaleDao {


    @Override
    public void completeSale(Sale sale) {
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
    public Integer destroy(Long id) {
        throw new UnsupportedOperationException("OPERATION NOT PERMITTED");
    }

    @Override
    public String[] fieldsToStringArray(Sale sale, ResultSet rs) throws SQLException {
        return new String[0];
    }
}
