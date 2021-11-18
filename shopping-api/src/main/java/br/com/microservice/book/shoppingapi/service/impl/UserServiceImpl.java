package br.com.microservice.book.shoppingapi.service.impl;

import br.com.microservice.book.shoppingapi.service.UserService;
import br.com.microservice.book.shoppingclient.DTO.UserDTO;
import br.com.microservice.book.shoppingclient.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class UserServiceImpl implements UserService {
    @Override
    public UserDTO getUserByCPF(String cpf) {

        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/user/cpf/" + cpf;
            ResponseEntity<UserDTO> response = restTemplate.getForEntity(url, UserDTO.class);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException();
        }

    }
}
