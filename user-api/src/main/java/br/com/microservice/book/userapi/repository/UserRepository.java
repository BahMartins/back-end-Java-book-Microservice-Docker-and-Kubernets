package br.com.microservice.book.userapi.repository;

import br.com.microservice.book.userapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByCpf(String cpf);

    Optional<User> findById(Long id);

    List<User> queryByNameLike(String name);

}
