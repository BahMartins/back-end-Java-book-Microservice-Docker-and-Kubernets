package br.com.microservice.book.shoppingapi.service;

import br.com.microservice.book.shoppingclient.DTO.ShopDTO;
import br.com.microservice.book.shoppingclient.DTO.ShopReportDTO;

import java.util.Date;
import java.util.List;

public interface ReportService {

    List<ShopDTO> getShopsByFilter(Date startDate, Date endDate, Float minimumValue);

    ShopReportDTO getReportByDate(Date startDate, Date endDate);

}
