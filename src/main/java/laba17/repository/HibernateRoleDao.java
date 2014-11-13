package laba17.repository;

import laba17.domain.Role;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class to work with the table Role of database by Hibernate
 * Created by redko on 9/23/2014.
 */
@Repository
public class HibernateRoleDao implements RoleDao {

    /**
     * For logging
     */
    private static Log log = LogFactory.getLog(HibernateRoleDao.class);

    /**
     * For creating sessions
     */
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void create(Role role) {
        try {
            sessionFactory.getCurrentSession().save(role);
            log.trace("createRole operation was successful");
        } catch (HibernateException e) {
            log.error("createRole operation error", e);
            throw SessionFactoryUtils.convertHibernateAccessException(e);
        }
    }

    @Transactional
    public void update(Role role) {
        try {
            sessionFactory.getCurrentSession().update(role);
            log.trace("updateRole operation was successful");
        } catch (HibernateException e) {
            log.error("updateRole operation error", e);
            throw SessionFactoryUtils.convertHibernateAccessException(e);
        }
    }

    @Transactional
    public void remove(Role role) {
        try {
            sessionFactory.getCurrentSession().delete(role);
            log.trace("removeRole operation was successful");
        } catch (HibernateException e) {
            log.error("removeRole operation error", e);
            throw SessionFactoryUtils.convertHibernateAccessException(e);
        }
    }

    @Transactional
    public Role findByName(String name) {
        Role role;
        try {
            role = (Role) sessionFactory.getCurrentSession().createCriteria(Role.class).add(Restrictions.eq("name", name)).uniqueResult();
            log.trace("findByNameRole operation was successful");
        } catch (HibernateException e) {
            log.error("findByNameRole operation error", e);
            throw SessionFactoryUtils.convertHibernateAccessException(e);
        }
        return role;
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