package controller.jmockit;

import laba17.service.RoleDaoService;
import laba17.service.UserDaoService;
import laba17.validation.Form;
import laba17.web.ControllerRegistration;
import mockit.*;
import mockit.integration.junit4.JMockit;
import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

import static junit.framework.TestCase.assertEquals;

/**
 * Test class for ControllerRegistration
 * Created by redko on 10/7/2014.
 */
@RunWith(JMockit.class)
public class RegistrationUserControllerTest {

    @Tested
    private ControllerRegistration controller;

    @Injectable
    private UserDaoService userDaoService;

    @Injectable
    private RoleDaoService roleDaoService;

    @Injectable
    private ReCaptcha reCaptcha;

    @Injectable
    private ReCaptchaResponse reCaptchaResponse;

    @Test
    public void goToRegistrationPageTest(@Mocked final Model model) {
        assertEquals("Registration", controller.goToRegistrationPage(model));
        new Verifications() {{
            model.addAttribute("validationForm", (Form) any);
        }};
    }

    @Test
    public void registrationUserSuccessfully(@Mocked final HttpServletRequest request,
                                             @Mocked final String challengeField,
                                             @Mocked final String responseFiled,
                                             @Mocked final Form form,
                                             @Mocked final BindingResult bindingResult,
                                             @Mocked final Model model) {
        new NonStrictExpectations() {{
            reCaptcha.checkAnswer(anyString, anyString, anyString);
            result = reCaptchaResponse;
            reCaptchaResponse.isValid();
            result = true;
            bindingResult.hasErrors();
            result = false;
            form.getBirthday();
            result = "1995-03-13";
            form.getRole();
            result = "user";
        }};

        assertEquals("Login", controller.registrationUser(request, challengeField, responseFiled, form, bindingResult, model));
    }

    @Test
    public void registrationUserError(@Mocked final HttpServletRequest request,
                                      @Mocked final String challengeField,
                                      @Mocked final String responseFiled,
                                      @Mocked final Form form,
                                      @Mocked final BindingResult bindingResult,
                                      @Mocked final Model model) {
        new NonStrictExpectations() {{
            reCaptcha.checkAnswer(anyString, anyString, anyString);
            result = reCaptchaResponse;
            reCaptchaResponse.isValid();
            result = true;
            bindingResult.hasErrors();
            result = true;
            form.getBirthday();
            result = "1995-03-13";
        }};

        assertEquals("Registration", controller.registrationUser(request, challengeField, responseFiled, form, bindingResult, model));
    }

    @Test
    public void registrationUserErrorCaptcha(@Mocked final HttpServletRequest request,
                                             @Mocked final String challengeField,
                                             @Mocked final String responseFiled,
                                             @Mocked final Form form,
                                             @Mocked final BindingResult bindingResult,
                                             @Mocked final Model model) {
        new NonStrictExpectations() {{
            reCaptcha.checkAnswer(anyString, anyString, anyString);
            result = reCaptchaResponse;
            reCaptchaResponse.isValid();
            result = false;
            bindingResult.hasErrors();
            result = false;
            form.getBirthday();
            result = "1995-03-13";
        }};
        model.addAttribute("err_message", "Error captcha");
        assertEquals("Registration", controller.registrationUser(request, challengeField, responseFiled, form, bindingResult, model));
    }

}
