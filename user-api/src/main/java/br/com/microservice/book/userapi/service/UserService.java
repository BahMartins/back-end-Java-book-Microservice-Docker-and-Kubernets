package br.com.microservice.book.userapi.service;

import br.com.microservice.book.userapi.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    Page<UserDTO> findAllUsers(Pageable pageable);

    ResponseEntity<UserDTO> findUserById(Long id);

    ResponseEntity<UserDTO> save(UserDTO userDTO);

    ResponseEntity<?> delete(Long id);

    ResponseEntity<UserDTO> findUserByCpf(String cpf);

    ResponseEntity<List<UserDTO>> queryByName(String name);
}
