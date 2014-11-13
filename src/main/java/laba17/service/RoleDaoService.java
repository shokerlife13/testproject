package laba17.service;

import laba17.domain.Role;

/**
 * Interface to the role table (Service)
 *
 * @author redko
 */
public interface RoleDaoService {
    /**
     * Method for creating a new role in the table
     *
     * @param role New role
     */
    public void create(Role role);

    /**
     * Method for update role
     *
     * @param role Updatable role
     */
    public void update(Role role);

    /**
     * Method for removing role from the table
     *
     * @param role Removable role
     */
    public void remove(Role role);

    /**
     * Method for finding role by name
     *
     * @param name Name
     * @return Role
     */
    public Role findByName(String name);
}
