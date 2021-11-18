package br.com.microservice.book.userapi.dto;

import br.com.microservice.book.userapi.model.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String cpf;
    private String address;
    private String email;
    private String phone;
    private LocalDate registerDate;


    public static UserDTO convert(User user){
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setCpf(user.getCpf());
        userDto.setAddress(user.getAddress());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setRegisterDate(user.getRegisterDate());
        return userDto;
    }
}
