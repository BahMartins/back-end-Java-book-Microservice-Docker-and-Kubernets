package br.com.microservice.book.shoppingclient.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorDTO {

    private Integer status;
    private String message;
    private Date timestamp;
}
