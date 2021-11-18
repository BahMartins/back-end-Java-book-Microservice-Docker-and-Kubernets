package br.com.microservice.book.shoppingapi.service;


import br.com.microservice.book.shoppingclient.DTO.ShopDTO;

import java.util.List;

public interface ShopService {

    List<ShopDTO> getAll();

    List<ShopDTO> getByUser(String userIdentifier);

    List<ShopDTO> getByDate(ShopDTO shopDto);

    ShopDTO findById(Long productId);

    ShopDTO save(ShopDTO shopDto);
}
