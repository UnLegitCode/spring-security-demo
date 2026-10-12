package ru.unlegit.securitydemo.repository;

import org.springframework.data.repository.ListCrudRepository;
import ru.unlegit.securitydemo.model.User;

import java.util.Optional;

public interface UserRepository extends ListCrudRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}