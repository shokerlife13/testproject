package controller.powermock;

import laba17.domain.Role;
import laba17.domain.User;
import laba17.service.RoleDaoService;
import laba17.service.UserDaoService;
import laba17.validation.Form;
import laba17.web.Controller;
import laba17.web.ControllerEdit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import java.sql.Date;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.*;
import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * Created by redko on 11/4/2014.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ControllerEdit.class})
public class EditUserControllerTest {

    @InjectMocks
    private ControllerEdit controller;

    @Mock
    private UserDaoService userDaoService;

    @Mock
    private RoleDaoService roleDaoService;

    @Test
    public void goToEditPageTest() {
        Model model = mock(Model.class);
        final User user = new User();
        user.setLogin("test");
        user.setPassword("test");
        user.setEmail("test@gmail.com");
        user.setFirstName("test");
        user.setLastName("test");
        user.setBirthday(Date.valueOf("1995-03-13"));
        user.setId_role(new Role(1, "Administrator"));
        when(userDaoService.findById(anyLong())).thenReturn(user);
        assertEquals("EditUser", controller.goToEditPage(1L, model));
        verify(model).addAttribute(eq("validationFormEdit"), any(Form.class));
    }

    @Test
    public void editUserSuccessfully() {
        HttpSession httpSession = mock(HttpSession.class);
        Form form = mock(Form.class);
        BindingResult bindingResult = mock(BindingResult.class);
        PowerMockito.when(userDaoService.findByLogin(anyString())).thenReturn(new User());
        PowerMockito.when(bindingResult.hasErrors()).thenReturn(false);
        PowerMockito.when(form.getPassword()).thenReturn("test");
        PowerMockito.when(form.getPasswordAgain()).thenReturn("test");
        PowerMockito.when(form.getBirthday()).thenReturn("1995-03-13");
        PowerMockito.when(form.getRole()).thenReturn("User");
        assertEquals("Admin", controller.editUser(form, bindingResult, httpSession));
    }

    @Test
    public void editAdminSuccessfully() {
        HttpSession httpSession = mock(HttpSession.class);
        Form form = mock(Form.class);
        BindingResult bindingResult = mock(BindingResult.class);
        PowerMockito.when(userDaoService.findByLogin(anyString())).thenReturn(new User());
        PowerMockito.when(bindingResult.hasErrors()).thenReturn(false);
        PowerMockito.when(form.getPassword()).thenReturn("test");
        PowerMockito.when(form.getPasswordAgain()).thenReturn("test");
        PowerMockito.when(form.getBirthday()).thenReturn("1995-03-13");
        PowerMockito.when(form.getRole()).thenReturn("Administrator");
        assertEquals("Admin", controller.editUser(form, bindingResult, httpSession));
    }

    @Test
    public void editError() {
        HttpSession httpSession = mock(HttpSession.class);
        Form form = mock(Form.class);
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        assertEquals("EditUser", controller.editUser(form, bindingResult, httpSession));
    }

}
