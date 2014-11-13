package laba17.repository;

import laba17.domain.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class to work with the table User of database by Hibernate
 * Created by redko on 9/23/2014.
 */
@Repository
public class HibernateUserDao implements UserDao {

    /**
     * For logging
     */
    private static Log log = LogFactory.getLog(HibernateUserDao.class);

    /**
     * For creating sessions
     */
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void create(User user) {
        try {
            sessionFactory.getCurrentSession().save(user);
            log.trace("createUser operation was successful");
        } catch (HibernateException e) {
            log.error("createUser operation error", e);
            throw SessionFactoryUtils.convertHibernateAccessException(e);
        }
    }

    @Transactional
    public void update(User user) {
        try {
            sessionFactory.getCurrentSession().update(user);
            log.trace("updateUser operation was successful");
        } catch (HibernateException e) {
            log.error("updateUser operation error", e);
            throw SessionFactoryUtils.convertHibernateAccessException(e);
        }
    }

    @Transactional
    public void remove(User user) {
        try {
            sessionFactory.getCurrentSession().delete(user);
            log.trace("removeUser operation was successful");
        } catch (HibernateException e) {
            log.error("removeUser operation error", e);
            throw SessionFactoryUtils.convertHibernateAccessException(e);
        }
    }

    @Transactional
    public List<User> findAll() {
        List<User> userList;
        try {
            userList = sessionFactory.getCurrentSession().createCriteria(User.class).list();
            log.trace("findAllUsers operation was successful");
        } catch (HibernateException e) {
            log.error("findAllUsers operation error", e);
            throw SessionFactoryUtils.convertHibernateAccessException(e);
        }
        return userList;
    }

    @Transactional
    public User findByLogin(String login) {
        User user;
        try {
            user = (User) sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("login", login)).uniqueResult();
            log.trace("findByLoginUser operation was successful");
        } catch (HibernateException e) {
            log.error("findByLoginUser operation error", e);
            throw SessionFactoryUtils.convertHibernateAccessException(e);
        }
        return user;
    }

    @Transactional
    public User findByEmail(String email) {
        User user;
        try {
            user = (User) sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
            log.trace("findByEmailUser operation was successful");
        } catch (HibernateException e) {
            log.error("findByEmailUser operation error", e);
            throw SessionFactoryUtils.convertHibernateAccessException(e);
        }
        return user;
    }

    @Transactional
    public User findById(long id) {
        User user;
        try {
            user = (User) sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("id", id)).uniqueResult();
            log.trace("findByIdUser operation was successful");
        } catch (HibernateException e) {
            log.error("findByIdUser operation error", e);
            throw SessionFactoryUtils.convertHibernateAccessException(e);
        }
        return user;
    }

    /**
     * Get sessionFactory
     *
     * @return sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Set sessionFactory
     *
     * @param sessionFactory session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
