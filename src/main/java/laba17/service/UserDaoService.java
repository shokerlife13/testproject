package laba17.service;

import laba17.domain.User;

import java.util.List;

/**
 * Interface to the user table (Service)
 *
 * @author redko
 */
public interface UserDaoService {
    /**
     * Method for creating a new user in the table
     *
     * @param user New user
     */
    public void create(User user);

    /**
     * Method for update user
     *
     * @param user Updatable user
     */
    public void update(User user);

    /**
     * Method for removing user from the table
     *
     * @param user Removable user
     */
    public void remove(User user);

    /**
     * Method for finding all users in the table
     *
     * @return List of users
     */
    public List<User> findAll();

    /**
     * Method for finding user by login
     *
     * @param login Login
     * @return User
     */
    public User findByLogin(String login);

    /**
     * Method for finding user by email
     *
     * @param email Email
     * @return User
     */
    public User findByEmail(String email);

    /**
     * Method for finding user by id
     *
     * @param id Id
     * @return User
     */
    public User findById(long id);
}
