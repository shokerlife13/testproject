package laba17.validation;

/**
 * Validation form
 * Created by redko on 10/1/2014.
 */
public class Form {

    private String login;
    private String password;
    private String passwordAgain;
    private String email;
    private String firstName;
    private String lastName;
    private String birthday;
    private String role;

    /**
     * Get login
     *
     * @return user login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set login
     *
     * @param login user login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Get password
     *
     * @return user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set password
     *
     * @param password user password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get password again
     *
     * @return user password again
     */
    public String getPasswordAgain() {
        return passwordAgain;
    }

    /**
     * Set password again
     *
     * @param passwordAgain user password again
     */
    public void setPasswordAgain(String passwordAgain) {
        this.passwordAgain = passwordAgain;
    }

    /**
     * Get email
     *
     * @return user email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email
     *
     * @param email user email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get first name
     *
     * @return user first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set first name
     *
     * @param firstName user first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get last name
     *
     * @return user last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set last name
     *
     * @param lastName user last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get birthday
     *
     * @return user birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * Set birthday
     *
     * @param birthday user birthday
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * Get role
     *
     * @return user role
     */
    public String getRole() {
        return role;
    }

    /**
     * Set role
     *
     * @param role user role
     */
    public void setRole(String role) {
        this.role = role;
    }

}
