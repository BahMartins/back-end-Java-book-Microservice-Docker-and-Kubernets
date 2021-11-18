package br.com.microservice.book.userapi.controller;

import br.com.microservice.book.userapi.dto.UserDTO;
import br.com.microservice.book.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public Page<UserDTO> findUsers(Pageable pageable){
        return userService.findAllUsers(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUser(@PathVariable(value = "id") Long id){
        return userService.findUserById(id);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UserDTO> findUserByCpf(@PathVariable(value = "cpf") String cpf){
        return userService.findUserByCpf(cpf);
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> queryByName(@RequestParam(name = "name", required = true) String name){
        return userService.queryByName(name);
    }

    @PostMapping("/newUser")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDto){
        return userService.save(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return userService.delete(id);

    }
}
