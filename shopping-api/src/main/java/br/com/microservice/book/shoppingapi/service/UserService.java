package br.com.microservice.book.shoppingapi.service;

import br.com.microservice.book.shoppingclient.DTO.UserDTO;

public interface UserService {

    UserDTO getUserByCPF(String cpf);
}
