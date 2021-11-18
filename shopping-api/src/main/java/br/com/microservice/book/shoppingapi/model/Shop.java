package br.com.microservice.book.shoppingapi.model;

import br.com.microservice.book.shoppingclient.DTO.ShopDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    private String userIdentifier;

    @Getter @Setter
    private BigDecimal total;

    @Getter @Setter
    private Date date;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "item", joinColumns = @JoinColumn(name = "shop_id"))
    @Getter @Setter
    private List<Item> items;

    public static Shop convert(ShopDTO shopDto){
        Shop shop = new Shop();
        shop.setUserIdentifier(shopDto.getUserIdentifier());
        shop.setTotal(shopDto.getTotal());
        shop.setDate(shopDto.getDate());
        shop.setItems(shopDto.getItems()
                .stream()
                .map(Item::convert)
                .collect(Collectors.toList()));

        return shop;
    }


}
