package br.com.microservice.book.userapi.service.impl;

import br.com.microservice.book.shoppingclient.exceptions.UserNotFoundException;
import br.com.microservice.book.userapi.dto.UserDTO;
import br.com.microservice.book.userapi.model.User;
import br.com.microservice.book.userapi.repository.UserRepository;
import br.com.microservice.book.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<UserDTO> findAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        List<UserDTO> userDTOList = users.stream().map(UserDTO::convert).collect(Collectors.toList());
        return new PageImpl<>(userDTOList);
    }

    @Override
    public ResponseEntity<UserDTO> findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Optional<UserDTO> userDTO = user.map(UserDTO::convert);
        return new ResponseEntity<UserDTO>(userDTO.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> findUserByCpf(String cpf) {
        User user = userRepository.findByCpf(cpf);
        if (user == null){
            throw new UserNotFoundException();
        }
        return new ResponseEntity<UserDTO>(UserDTO.convert(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserDTO>> queryByName(String name) {
        List<User> users = userRepository.queryByNameLike(name);
        if(users.isEmpty()){
            ResponseEntity.notFound().build();
        }
        List<UserDTO> usersDto = users.stream().map(UserDTO::convert).collect(Collectors.toList());
        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> save(UserDTO userDTO) {
        User user = userRepository.save(User.convert(userDTO));
        URI uri = createUri(user);
        return ResponseEntity.created(uri).body(UserDTO.convert(user));
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()){
            return ResponseEntity.notFound().build();
        }
        userRepository.delete(user.get());
        return ResponseEntity.ok().build();
    }

    private URI createUri(User user) {
        ServletUriComponentsBuilder currentRequest = ServletUriComponentsBuilder.fromCurrentRequest();
        return currentRequest.path("/{id}").buildAndExpand(UserDTO.convert(user).getId()).toUri();
    }
}
