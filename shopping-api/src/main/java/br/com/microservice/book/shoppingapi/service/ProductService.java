package br.com.microservice.book.shoppingapi.service;

import br.com.microservice.book.shoppingclient.DTO.ProductDTO;

public interface ProductService {

    ProductDTO getProductByIdentifier(String productIdentifier);
}
