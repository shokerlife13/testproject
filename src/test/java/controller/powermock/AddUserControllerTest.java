package controller.powermock;

import laba17.service.RoleDaoService;
import laba17.service.UserDaoService;
import laba17.validation.Form;
import laba17.web.Controller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by redko on 11/4/2014.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Controller.class})
public class AddUserControllerTest {

    @InjectMocks
    private Controller controller;

    @Mock
    private UserDaoService userDaoService;

    @Mock
    private RoleDaoService roleDaoService;

    @Test
    public void testGoToAddPage() {
        Model model = mock(Model.class);
        assertEquals("AddUser", controller.goToAddPage(model));
        verify(model).addAttribute(eq("validationForm"), any(Form.class));
    }

    @Test
    public void testAddUserSuccessfully() {
        Form form = mock(Form.class);
        BindingResult bindingResult = mock(BindingResult.class);
        HttpSession httpSession = mock(HttpSession.class);
        when(form.getBirthday()).thenReturn("1995-03-13");
        when(form.getRole()).thenReturn("user");
        assertEquals("Admin", controller.addUser(form, bindingResult, httpSession));
    }

    @Test
    public void testAddAdminSuccessfully() {
        Form form = mock(Form.class);
        BindingResult bindingResult = mock(BindingResult.class);
        HttpSession httpSession = mock(HttpSession.class);
        when(form.getBirthday()).thenReturn("1995-03-13");
        when(form.getRole()).thenReturn("administrator");
        assertEquals("Admin", controller.addUser(form, bindingResult, httpSession));
    }

    @Test
    public void testAddUserError() {
        Form form = mock(Form.class);
        BindingResult bindingResult = mock(BindingResult.class);
        HttpSession httpSession = mock(HttpSession.class);
        when(bindingResult.hasErrors()).thenReturn(true);
        assertEquals("AddUser", controller.addUser(form, bindingResult, httpSession));
    }

}
