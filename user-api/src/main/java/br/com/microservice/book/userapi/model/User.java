package br.com.microservice.book.userapi.model;

import br.com.microservice.book.userapi.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String cpf;

    @Getter @Setter
    private String address;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String phone;

    @Getter @Setter
    private LocalDate registerDate;

    public static User convert(UserDTO userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setAddress(userDto.getAddress());
        user.setCpf(userDto.getCpf());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setRegisterDate(userDto.getRegisterDate());
        return user;
    }
}
