package br.com.microservice.book.shoppingapi.converter;

import br.com.microservice.book.shoppingapi.model.Item;
import br.com.microservice.book.shoppingapi.model.Shop;
import br.com.microservice.book.shoppingclient.DTO.ItemDTO;
import br.com.microservice.book.shoppingclient.DTO.ShopDTO;

import java.util.stream.Collectors;


public class DTOConverter {
	
	public static ItemDTO convert(Item item) {
		ItemDTO itemDto = new ItemDTO();
		itemDto.setProductIdentifier(item.getProductIdentifier());
		itemDto.setPrice(item.getPrice());
		return itemDto;
	}
	
	public static ShopDTO convert(Shop shop) {
		ShopDTO shopDto = new ShopDTO();
		shopDto.setUserIdentifier(shop.getUserIdentifier());
		shopDto.setTotal(shop.getTotal());
		shopDto.setDate(shop.getDate());
		shopDto.setItems(shop.getItems()
				.stream()
				.map(DTOConverter::convert)
				.collect(Collectors.toList()));
		return shopDto;
	}

}
