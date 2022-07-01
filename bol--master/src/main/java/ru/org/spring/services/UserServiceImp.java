package ru.org.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.org.spring.dao.UserDao;
import ru.org.spring.model.User;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Repository
public class UserServiceImp implements UserService {
    private UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public List<User> findAll() {
        return userDao.index1();
    }

    @Override
    public void deleteById(Long aLong) {
        userDao.delete1(aLong);
    }


    @Override
    public User save(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(user.getPassword());
        user.setPassword(password);
        userDao.save1(user);
        return user;
    }


    @Override
    public User getById(Long aLong) {
        return userDao.show1(aLong);
    }

    @Override
    public void update(Long id, User updatedPerson) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(updatedPerson.getPassword());
        updatedPerson.setPassword(password);
        userDao.update1(id, updatedPerson);
    }


}
