package br.com.microservice.book.shoppingclient.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {

    private String name;
    private String cpf;
    private String address;
    private String email;
    private String phone;
    private LocalDate registerDate;
}
