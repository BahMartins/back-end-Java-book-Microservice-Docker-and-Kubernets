package br.com.microservice.book.shoppingclient.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ShopDTO {

    @NotBlank
    private String userIdentifier;

    @NotNull
    private BigDecimal total;

    @NotNull
    private Date date;

    @NotNull
    private List<ItemDTO> items;
}
