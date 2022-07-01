package ru.org.spring.services;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.org.spring.model.User;

public interface UserDetailsHelp extends JpaRepository<User, Long> {
    User findByName(String name);
}
