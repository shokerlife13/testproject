package laba17.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Class representing the role
 *
 * @author redko
 */
@Entity
@Table(name = "ROLE")
public class Role {

    /**
     * idRole Role id
     */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "IDROLE")
    private long idRole;
    /**
     * name Name
     */

    @Column(name = "NAME")
    private String name;

    /**
     * Default constructor
     */
    public Role() {
    }

    /**
     * Constructor for creating role (with role id)
     *
     * @param id_role Role id
     * @param name    Name
     */
    public Role(long id_role, String name) {
        this.setId_role(id_role);
        this.setName(name);
    }

    /**
     * Constructor for creating role (without role id)
     *
     * @param name Name
     */
    public Role(String name) {
        this.setName(name);
    }

    /**
     * Get name
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     *
     * @param name Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get role id
     *
     * @return Role id
     */
    public long getId_role() {
        return idRole;
    }

    /**
     * Set role id
     *
     * @param id_role Role id
     */
    public void setId_role(long id_role) {
        this.idRole = id_role;
    }

    @Override
    public String toString() {
        return "IDROLE " + idRole + "| NAME " + name;
    }

    @Override
    public boolean equals(Object obj) {
        Role role = (Role) obj;
        if (obj == null) {
            return false;
        }
        if (this.getClass() != role.getClass()) {
            return false;
        }
        if (this.getId_role() == role.getId_role() && this.getName() == role.getName()) {
            return true;
        } else {
            return false;
        }
    }
}
