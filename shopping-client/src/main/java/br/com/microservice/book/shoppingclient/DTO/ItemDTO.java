package br.com.microservice.book.shoppingclient.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class ItemDTO {

    @NotBlank
    private String productIdentifier;

    @NotNull
    private BigDecimal price;
}
