package laba17.validation;

import laba17.service.UserDaoService;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import java.sql.Date;

/**
 * Class for validation of add-form fields
 * Created by redko on 10/1/2014.
 */
public class FormValidator implements org.springframework.validation.Validator {

    @Autowired
    private UserDaoService userDaoService;

    public boolean supports(Class<?> aClass) {
        return Form.class.equals(aClass);
    }

    public void validate(Object target, Errors errors) {
        Form validationForm = (Form) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "login.empty", "Login must be not empty");

        if (loginExist(validationForm.getLogin())) {
            errors.rejectValue("login", "login.notValid", "Login is already exist");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Password must be not empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordAgain", "passwordAgain.empty", "Password again must be not empty");

        if (!(validationForm.getPassword()).equals(validationForm.getPasswordAgain())) {
            errors.rejectValue("passwordAgain", "passwordAgain.passwordDontMatch", "Passwords don't match");
        }

        if (!EmailValidator.getInstance().isValid(validationForm.getEmail())) {
            errors.rejectValue("email", "email.notValid", "Email is not valid");
        }

        if (emailExist(validationForm.getEmail())) {
            errors.rejectValue("email", "email.notValid", "Email is already exist");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "firstName.empty", "First name must be not empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.empty", "Last name must be not empty");

        if (!validDate(validationForm.getBirthday())) {
            errors.rejectValue("birthday", "birthday.notValid", "Birthday is not valid");
        }
    }

    /**
     * Method, which checks the validity of the entered date of birthday
     *
     * @param birthday user birthday
     * @return true, if date is valid, otherwise false
     */
    private boolean validDate(String birthday) {
        try {
            Date.valueOf(birthday);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method that checks whether there is a same login
     *
     * @param login user login
     * @return true, if login exists, otherwise false
     */
    private boolean loginExist(String login) {
        if (userDaoService.findByLogin(login) == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method that checks whether there is a same email
     *
     * @param email user email
     * @return true, if email exists, otherwise false
     */
    private boolean emailExist(String email) {
        if (userDaoService.findByEmail(email) == null) {
            return false;
        } else {
            return true;
        }
    }
}
