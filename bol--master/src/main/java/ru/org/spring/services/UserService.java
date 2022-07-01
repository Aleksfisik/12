package ru.org.spring.services;

import ru.org.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserService {
    List<User> findAll();

    User save(User user);

    void deleteById(Long aLong);

    User getById(Long aLong);

    void update(Long id, User updatedPerson);
}
