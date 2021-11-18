package br.com.microservice.book.shoppingapi.model;

import br.com.microservice.book.shoppingclient.DTO.ItemDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Embeddable
public class Item {

    @Getter @Setter
    private String productIdentifier;

    @Getter @Setter
    private BigDecimal price;

    public static Item convert(ItemDTO itemDto){
        Item item = new Item();
        item.setProductIdentifier(itemDto.getProductIdentifier());
        item.setPrice(itemDto.getPrice());
        return item;
    }
}
