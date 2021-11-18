package br.com.microservice.book.shoppingapi.repository;

import br.com.microservice.book.shoppingapi.model.Shop;
import br.com.microservice.book.shoppingclient.DTO.ShopReportDTO;

import java.util.Date;
import java.util.List;

public interface ReportRepository {

    List<Shop> getShopByFilters(Date startDate, Date endDate, Float minimumValue);

    ShopReportDTO getReportByDate(Date startDate, Date endDate);

}
