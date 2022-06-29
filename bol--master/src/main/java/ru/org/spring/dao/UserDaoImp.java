package ru.org.spring.dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.org.spring.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
public class UserDaoImp implements UserDao{
    @PersistenceContext()
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<User> index1() {

        return entityManager.createQuery("SELECT user FROM User user", User.class).getResultList();
    }

    @Override
    @Transactional
    public User show1(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void save1(User person) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode(person.getPassword());
        person.setPassword(password);

        entityManager.persist(person);
        entityManager.flush();
    }

    @Override
    @Transactional
    public void update1(Long id, User updatedPerson) {
        entityManager.find(User.class, id);
        entityManager.merge(updatedPerson);
        entityManager.flush();
    }
    @Override
    @Transactional
    public User readPerson(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void delete1(Long id) {
        User user = readPerson(id);
        entityManager.remove(user);
        entityManager.flush();

    }
}
