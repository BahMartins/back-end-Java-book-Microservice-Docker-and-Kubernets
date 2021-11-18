package br.com.microservice.book.productapi.service;

import br.com.microservice.book.shoppingclient.DTO.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getAll();

    List<ProductDTO> getProductByCategoryId(Long categoryId);

    ProductDTO findByProductIdentifier(String productIdentifier);

    ProductDTO save(ProductDTO productDto);

    ProductDTO delete(Long productId);

}
