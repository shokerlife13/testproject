package laba17.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

/**
 * Class representing the user
 *
 * @author redko
 */
@Entity
@Table(name = "USER")
public class User {

    /**
     * idUser User id
     */
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "IDUSER")
    private long idUser;
    /**
     * idRole Role id
     */
    @ManyToOne
    @JoinColumn(name = "IDROLE")
    private Role idRole;
    /**
     * login Login
     */
    @Column(name = "LOGIN")
    private String login;
    /**
     * password Password
     */
    @Column(name = "PASSWORD")
    private String password;
    /**
     * email Email
     */
    @Column(name = "EMAIL")
    private String email;
    /**
     * firstName First name
     */
    @Column(name = "FIRSTNAME")
    private String firstName;
    /**
     * birthday Birthday
     */
    @Column(name = "BIRTHDAY")
    private Date birthday;
    /**
     * lastName Last name
     */
    @Column(name = "LASTNAME")
    private String lastName;

    /**
     * Default constructor
     */
    public User() {
    }

    /**
     * Constructor for creating user (with id)
     *
     * @param id_user   User id
     * @param id_role   Role id
     * @param login     Login
     * @param password  Password
     * @param email     Email
     * @param firstName First name
     * @param lastName  Last name
     * @param birthday  Birthday
     */
    public User(long id_user, Role id_role, String login, String password,
                String email, String firstName, String lastName, Date birthday) {
        this.setId_user(id_user);
        this.setId_role(id_role);
        this.setLogin(login);
        this.setPassword(password);
        this.setEmail(email);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setBirthday(birthday);
    }

    /**
     * Constructor for creating user (without id)
     *
     * @param id_role   Role id
     * @param login     Login
     * @param password  Password
     * @param email     Email
     * @param firstName First name
     * @param lastName  Last name
     * @param birthday  Birthday
     */
    public User(Role id_role, String login, String password, String email,
                String firstName, String lastName, Date birthday) {
        this.setId_role(id_role);
        this.setLogin(login);
        this.setPassword(password);
        this.setEmail(email);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setBirthday(birthday);
    }

    /**
     * Get role id
     *
     * @return Role id
     */
    public Role getId_role() {
        return idRole;
    }

    /**
     * Set role id
     *
     * @param idRole Role id
     */
    public void setId_role(Role idRole) {
        this.idRole = idRole;
    }

    /**
     * Get birthday
     *
     * @return Birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * Set birthday
     *
     * @param birthday Birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * Get last name
     *
     * @return Last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set last name
     *
     * @param lastName Last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get first name
     *
     * @return First name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set first name
     *
     * @param firstName First name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get email
     *
     * @return Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email
     *
     * @param email Email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get password
     *
     * @return Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password
     *
     * @param password Password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get user id
     *
     * @return User id
     */
    public long getId_user() {
        return idUser;
    }

    /**
     * Set user id
     *
     * @param idUser User id
     */
    public void setId_user(long idUser) {
        this.idUser = idUser;
    }

    /**
     * Get login
     *
     * @return Login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set login
     *
     * @param login Login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "ID: " + idUser + "| IDROLE: " + idRole + "| LOGIN: " + login
                + "| PASSWORD: " + password + "| EMAIL: " + email
                + "| FIRSTNAME: " + firstName + "| LASTNAME: " + lastName
                + "| BIRTHDAY: " + birthday + "\n";
    }

    @Override
    public boolean equals(Object obj) {
        User user = (User) obj;
        if (obj == null) {
            return false;
        }
        if (this.getClass() != user.getClass()) {
            return false;
        }
        if (this.getId_user() == user.getId_user() && this.getId_role() == user.getId_role()
                && this.getLogin() == user.getLogin() && this.getPassword() == user.getPassword()
                && this.getEmail() == user.getEmail() && this.getFirstName() == user.getFirstName()
                && this.getLastName() == user.getLastName() && this.getBirthday() == user.getBirthday()) {
            return true;
        } else {
            return false;
        }
    }
}
