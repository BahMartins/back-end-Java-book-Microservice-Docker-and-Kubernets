package br.com.microservice.book.shoppingapi.service.impl;

import br.com.microservice.book.shoppingapi.service.ProductService;
import br.com.microservice.book.shoppingclient.DTO.ProductDTO;
import br.com.microservice.book.shoppingclient.exceptions.ProductNotFoundException;
import br.com.microservice.book.shoppingclient.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class ProductServiceImpl implements ProductService {

    @Override
    public ProductDTO getProductByIdentifier(String productIdentifier) {

        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/product" + productIdentifier;
            ResponseEntity<ProductDTO> response = restTemplate.getForEntity(url, ProductDTO.class);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new ProductNotFoundException();
        }

    }

}
