package br.com.microservice.book.shoppingclient.DTO;

import lombok.Data;

@Data
public class ShopReportDTO {

    private Integer count;
    private Double total;
    private Double mean;
}
