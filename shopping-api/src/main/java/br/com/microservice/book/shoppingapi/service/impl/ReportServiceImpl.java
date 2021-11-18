package br.com.microservice.book.shoppingapi.service.impl;

import br.com.microservice.book.shoppingapi.converter.DTOConverter;
import br.com.microservice.book.shoppingapi.model.Shop;
import br.com.microservice.book.shoppingapi.repository.ReportRepository;
import br.com.microservice.book.shoppingapi.service.ReportService;
import br.com.microservice.book.shoppingclient.DTO.ShopDTO;
import br.com.microservice.book.shoppingclient.DTO.ShopReportDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;


    @Override
    public List<ShopDTO> getShopsByFilter(Date startDate, Date endDate, Float minimumValue) {
        List<Shop> shops = reportRepository.getShopByFilters(startDate, endDate, minimumValue);
        return shops.stream()
                .map(DTOConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public ShopReportDTO getReportByDate(Date startDate, Date endDate) {
        return reportRepository.getReportByDate(startDate, endDate);
    }
}
