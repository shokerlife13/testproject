package controller.powermock;

import laba17.service.RoleDaoService;
import laba17.service.UserDaoService;
import laba17.validation.Form;
import laba17.web.Controller;
import laba17.web.ControllerRegistration;
import mockit.Injectable;
import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * Created by redko on 11/4/2014.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ControllerRegistration.class})
public class RegistrationUserControllerTest {

    @InjectMocks
    private ControllerRegistration controller;

    @Mock
    private UserDaoService userDaoService;

    @Mock
    private RoleDaoService roleDaoService;

    @Mock
    private ReCaptcha reCaptcha;

    @Mock
    private ReCaptchaResponse reCaptchaResponse;

    @Test
    public void goToRegistrationPageTest() {
        Model model = mock(Model.class);
        assertEquals("Registration", controller.goToRegistrationPage(model));
        verify(model).addAttribute(eq("validationForm"), any(Form.class));
    }

    @Test
    public void registrationUserSuccessfully() {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        String challengeField = mock(String.class);
        String responseField = mock(String.class);
        Form form = mock(Form.class);
        BindingResult bindingResult = mock(BindingResult.class);
        Model model = mock(Model.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(form.getBirthday()).thenReturn("1995-03-13");
        when(form.getRole()).thenReturn("User");
        when(reCaptcha.checkAnswer(anyString(), anyString(), anyString())).thenReturn(reCaptchaResponse);
        when(reCaptchaResponse.isValid()).thenReturn(true);
        assertEquals("Login", controller.registrationUser(httpServletRequest, challengeField, responseField, form, bindingResult, model));
    }

    @Test
    public void registrationUserError() {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        String challengeField = mock(String.class);
        String responseField = mock(String.class);
        Form form = mock(Form.class);
        BindingResult bindingResult = mock(BindingResult.class);
        Model model = mock(Model.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        when(form.getBirthday()).thenReturn("1995-03-13");
        when(form.getRole()).thenReturn("User");
        when(reCaptcha.checkAnswer(anyString(), anyString(), anyString())).thenReturn(reCaptchaResponse);
        when(reCaptchaResponse.isValid()).thenReturn(true);
        assertEquals("Registration", controller.registrationUser(httpServletRequest, challengeField, responseField, form, bindingResult, model));
    }

    @Test
    public void registrationUserErrorCaptcha() {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        String challengeField = mock(String.class);
        String responseField = mock(String.class);
        Form form = mock(Form.class);
        BindingResult bindingResult = mock(BindingResult.class);
        Model model = mock(Model.class);
        when(bindingResult.hasErrors()).thenReturn(false);
        when(form.getBirthday()).thenReturn("1995-03-13");
        when(form.getRole()).thenReturn("User");
        when(reCaptcha.checkAnswer(anyString(), anyString(), anyString())).thenReturn(reCaptchaResponse);
        when(reCaptchaResponse.isValid()).thenReturn(false);
        assertEquals("Registration", controller.registrationUser(httpServletRequest, challengeField, responseField, form, bindingResult, model));
        verify(model).addAttribute("err_message", "Error captcha");
    }

}
