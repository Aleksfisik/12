package ru.org.spring.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.org.spring.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<User> index1() {

        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Transactional(readOnly = true)
    public User show1(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void save1(User person) {
        entityManager.persist(person);
        entityManager.flush();
    }

    @Transactional
    public void update1(Long id, User updatedPerson) {
        entityManager.find(User.class, id);
        entityManager.merge(updatedPerson);
        entityManager.flush();
    }

    public User readPerson(Long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void delete1(Long id) {
        User user = readPerson(id);
        entityManager.remove(user);
        entityManager.flush();

    }
}
