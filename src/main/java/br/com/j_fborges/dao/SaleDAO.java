package br.com.j_fborges.dao;

import br.com.j_fborges.dao.generic.GenericDAO;
import br.com.j_fborges.domain.Consumer;
import br.com.j_fborges.domain.Product;
import br.com.j_fborges.domain.Sale;
import br.com.j_fborges.exception.DAOException;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class SaleDAO extends GenericDAO<Sale, Long> implements ISaleDao {


    public SaleDAO() {
        super(Sale.class);
    }

    @Override
    public void completeSale(Sale sale) throws DAOException {
        sale.setStatus(Sale.Status.COMPLETED);
        super.update(sale);
    }

    @Override
    public void cancelSale(Sale sale) throws DAOException{
        sale.setStatus(Sale.Status.CANCELLED);
        super.update(sale);
    }

    @Override
    public void destroy(Sale sale) throws DAOException{
        throw new UnsupportedOperationException("OPERATION NOT PERMITTED");
    }

    @Override
    public Sale create(Sale entity) throws DAOException{
        try {
            openConnection();
            entity.getProducts().forEach( prodQntt -> {
                Product product = entityManager.merge(prodQntt.getProduct());
                prodQntt.setProduct(product);
            });

            Consumer consumer = entityManager.merge(entity.getConsumer());
            entity.setConsumer(consumer);
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            closeConnection();
            return entity;
        } catch (Exception e) {
            throw new DAOException( "ERROR SAVING SALE ", e);
        }
    }

    @Override
    public Sale findWithCollections(Long id) throws DAOException {
        openConnection();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Sale> query = builder.createQuery(Sale.class);
        Root<Sale> root = query.from(Sale.class);
        root.fetch("consumer");
        root.fetch("saleProducts");
        query.select(root).where(builder.equal(root.get("id"), id));
        TypedQuery<Sale> tpQuery =
                entityManager.createQuery(query);
        Sale venda = tpQuery.getSingleResult();
        closeConnection();
        return venda;
    }

    @Override
    public Class<Sale> getClassType() {
        return Sale.class;
    }

}
