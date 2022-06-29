package ru.org.spring.services;

import ru.org.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserServiceImp extends JpaRepository<User, Long> {
    User findByName(String name);
}
