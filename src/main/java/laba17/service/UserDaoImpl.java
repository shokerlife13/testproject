package laba17.service;

import laba17.domain.User;
import laba17.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class service to work with the table User of database
 * Created by redko on 9/29/2014.
 */
@Service
public class UserDaoImpl implements UserDaoService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public void create(User user) {
        userDao.create(user);
    }

    @Transactional
    public void update(User user) {
        userDao.update(user);
    }

    @Transactional
    public void remove(User user) {
        userDao.remove(user);
    }

    @Transactional
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Transactional
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Transactional
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Transactional
    public User findById(long id) {
        return userDao.findById(id);
    }
}
