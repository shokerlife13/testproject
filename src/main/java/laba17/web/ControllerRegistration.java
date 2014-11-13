package laba17.web;

import laba17.domain.User;
import laba17.service.RoleDaoService;
import laba17.service.UserDaoService;
import laba17.validation.Form;
import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

/**
 * Class that controls the user registration
 * Created by redko on 9/29/2014.
 */

@org.springframework.stereotype.Controller
public class ControllerRegistration {

    @Autowired
    private RoleDaoService roleDao;
    @Autowired
    private UserDaoService userDao;
    @Autowired
    private ReCaptcha reCaptcha;

    /**
     * Registration-form validator instance
     */
    @Autowired
    @Qualifier("formValidator")
    private Validator validator;

    /**
     * Setting validator
     *
     * @param binder WebDataBinder
     */
    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    /**
     * Creating registration-form
     *
     * @return registration-form
     */
    @ModelAttribute("validationForm")
    public Form createFormModel() {
        return new Form();
    }

    /**
     * Method redirects the user to a page EditUser
     *
     * @param model Model
     * @return Registration-page
     */
    @RequestMapping(value = "GoToRegistrationPage", method = RequestMethod.GET)
    public String goToRegistrationPage(Model model) {
        model.addAttribute("validationForm", new Form());
        return "Registration";
    }

    /**
     * @param request        request
     * @param challengeField reCaptcha request
     * @param responseField  reCaptcha response
     * @param form           form to be filled
     * @param bindingResult  result after filling
     * @param model          Model
     * @return one of the pages(Registration,Login)
     */
    @RequestMapping(value = "RegistrationAction", method = RequestMethod.POST)
    public String registrationUser(HttpServletRequest request, @RequestParam("recaptcha_challenge_field") String challengeField,
                                   @RequestParam("recaptcha_response_field") String responseField,
                                   @ModelAttribute("validationForm") @Validated Form form,
                                   BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "Registration";
        } else {
            ReCaptchaResponse response = reCaptcha.checkAnswer(request.getRemoteAddr(), challengeField, responseField);
            if (response.isValid()) {
                User user = new User();
                user.setLogin(form.getLogin());
                user.setPassword(form.getPassword());
                user.setEmail(form.getEmail());
                user.setFirstName(form.getFirstName());
                user.setLastName(form.getLastName());
                user.setBirthday(Date.valueOf(form.getBirthday()));
                user.setId_role(roleDao.findByName("USER"));
                userDao.create(user);
            } else {
                model.addAttribute("err_message", "Error captcha");
                return "Registration";
            }
        }
        return "Login";
    }

}
