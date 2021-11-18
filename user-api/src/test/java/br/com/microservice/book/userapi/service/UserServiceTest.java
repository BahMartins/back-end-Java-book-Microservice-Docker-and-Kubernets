package br.com.microservice.book.userapi.service;

import br.com.microservice.book.userapi.dto.UserDTO;
import br.com.microservice.book.userapi.model.User;
import br.com.microservice.book.userapi.repository.UserRepository;
import br.com.microservice.book.userapi.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Test
    public void shouldReturnAllUsers(){
        User user1 = createUser("Barbara");
        user1.setId(1L);
        User user2 = createUser("Aldria");
        user2.setId(2L);
        User user3 = createUser("Monica");
        user3.setId(3L);

        List<User> usersMock = Arrays.asList(user1, user2, user3);
        Pageable pageable = pageable();

        Mockito.when(userRepository.findAll(pageable)).thenReturn(new PageImpl<>(usersMock));
        Page<UserDTO> allUsers = userService.findAllUsers(pageable);

        assertEquals(3, allUsers.getTotalElements());
        verify(userRepository, times(1)).findAll(pageable);
    }

    @Test
    public void shouldFindUserById(){
        User user = createUser( "Barbara");

        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        ResponseEntity<UserDTO> userDto = userService.findUserById(user.getId());

        assertEquals(user.getName(), Objects.requireNonNull(userDto.getBody()).getName());
        assertEquals(HttpStatus.OK, userDto.getStatusCode());
        verify(userRepository, times(1)).findById(user.getId());
    }

    //continuar daqui
    @Test
    public void shouldFindUserByCpf(){
        User user = createUser("Barbara");

        Mockito.when(userRepository.findByCpf(user.getCpf())).thenReturn(user);
        //UserDTO userDto = userService.findByCpf(user.getCpf());

        //assertEquals(user.getCpf(), userDto.getCpf());
        verify(userRepository, times(1)).findByCpf(user.getCpf());
    }


    //Analisar com mais calma isso aqui
    @Test
    public void shouldFindUserByQueryByName(){
        User user1 = createUser("Barbara");
        user1.setId(1L);
        User user2 = createUser("Aldria");
        user2.setId(2L);

        List<User> usersMock = Arrays.asList(user1, user2);

        Mockito.when(userRepository.queryByNameLike(user1.getName())).thenReturn(usersMock);
        //List<UserDTO> usersDto = userService.queryByName(user1.getName());

        //assertEquals(2, usersDto.size());
        verify(userRepository, times(1)).queryByNameLike(user1.getName());
    }

    @Test
    public void shouldSaveUser(){
        User user = createUser("Barbara");
        UserDTO userDto = createUserDto("Barbara");

        Mockito.when(userRepository.save(user)).thenReturn(user);
        userService.save(userDto);

        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void shouldDeleteUser(){
        User user = createUser("Barbara");

        Mockito.doNothing().when(userRepository).delete(user);
        Mockito.when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        userService.delete(user.getId());

        verify(userRepository, times(1)).delete(user);
    }


    private User createUser(String name) {
        User user = new User();
        user.setId(1L);
        user.setName(name);
        user.setCpf("123456");
        user.setAddress("Rua 123");
        user.setEmail("user@user.com");
        user.setPhone("199123456");
        user.setRegisterDate(LocalDate.now());
        return user;
    }

    private UserDTO createUserDto(String name) {
        UserDTO userDto = new UserDTO();
        userDto.setId(1L);
        userDto.setName(name);
        userDto.setCpf("123456");
        userDto.setAddress("Rua 123");
        userDto.setEmail("user@user.com");
        userDto.setPhone("199123456");
        userDto.setRegisterDate(LocalDate.now());
        return userDto;
    }

    private Pageable pageable(){
        return new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 0;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public Pageable withPage(int pageNumber) {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
    }
}
