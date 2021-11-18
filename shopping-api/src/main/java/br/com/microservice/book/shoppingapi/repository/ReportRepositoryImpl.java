package br.com.microservice.book.shoppingapi.repository;

import br.com.microservice.book.shoppingapi.model.Shop;
import br.com.microservice.book.shoppingclient.DTO.ShopReportDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class ReportRepositoryImpl implements ReportRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Shop> getShopByFilters(Date startDate, Date endDate, Float minimumValue) {

        StringBuilder builder = new StringBuilder();
        builder.append("select s ");
        builder.append("from shop s ");
        builder.append("where s.date >= :startDate ");

        if(endDate != null){
            builder.append("and s.date <= :endDate");
        }
        if (minimumValue != null){
            builder.append("and s.total <= :minimumValue");
        }

        Query query = entityManager.createQuery(builder.toString());
        query.setParameter("starDate", startDate);
        if(endDate != null) {
            query.setParameter("endDate", endDate);
        }
        if (minimumValue != null){
            query.setParameter("minimumValue", minimumValue);
        }

        return query.getResultList();
    }

    @Override
    public ShopReportDTO getReportByDate(Date startDate, Date endDate) {

        StringBuilder builder = new StringBuilder();
        builder.append("select count(sp.id), sum(sp.total), avg(sp.total) ");
        builder.append("from shopping.shop sp ");
        builder.append("where sp.date >= :startDate ");
        builder.append("and sp.date <= :endDate ");

        Query query = entityManager.createNamedQuery(builder.toString());
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        Object[] result = (Object[]) query.getSingleResult();
        ShopReportDTO shopReportDTO = new ShopReportDTO();
        shopReportDTO.setCount( ((BigInteger) result[0]).intValue());
        shopReportDTO.setTotal((Double) result[1]);
        shopReportDTO.setMean((Double) result[2]);
        return shopReportDTO;
    }
}
