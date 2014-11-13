package controller.jmockit;

import laba17.domain.Role;
import laba17.domain.User;
import laba17.service.RoleDaoService;
import laba17.service.UserDaoService;
import laba17.validation.Form;
import laba17.web.ControllerEdit;
import mockit.Injectable;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;
import java.sql.Date;

import static junit.framework.TestCase.assertEquals;


/**
 * Test class for ControllerEdit
 * Created by redko on 10/7/2014.
 */
@RunWith(JMockit.class)
public class EditUserControllerTest {

    @Tested
    private ControllerEdit controller;

    @Injectable
    private UserDaoService userDaoService;

    @Injectable
    private RoleDaoService roleDaoService;

    @Injectable
    User user;

    @Test
    public void goToEditPageTest(@Mocked final Model model) {
        final User user = new User();
        user.setLogin("test");
        user.setPassword("test");
        user.setEmail("test@gmail.com");
        user.setFirstName("test");
        user.setLastName("test");
        user.setBirthday(Date.valueOf("1995-03-13"));
        user.setId_role(new Role(1, "Administrator"));
        new NonStrictExpectations() {{
            userDaoService.findById(anyLong);
            result = user;
            model.addAttribute("validationFormEdit", (Form) any);
        }};
        assertEquals("EditUser", controller.goToEditPage(1L, model));
    }

    @Test
    public void editUserSuccessfully(@Mocked final Form form,
                                     @Mocked final BindingResult bindingResult,
                                     @Mocked final HttpSession httpSession) {
        new NonStrictExpectations() {{
            userDaoService.findByLogin(anyString);
            result = new User();
            bindingResult.hasErrors();
            result = false;
            form.getBirthday();
            result = "1995-03-13";
            form.getRole();
            result = "user";
        }};

        String actualPath = controller.editUser(form, bindingResult, httpSession);
        assertEquals("Admin", actualPath);
    }

    @Test
    public void editAdminSuccessfully(@Mocked final Form form,
                                      @Mocked final BindingResult bindingResult,
                                      @Mocked final HttpSession httpSession) {
        new NonStrictExpectations() {{
            userDaoService.findByLogin(anyString);
            result = new User();
            bindingResult.hasErrors();
            result = false;
            form.getBirthday();
            result = "1995-03-13";
            form.getRole();
            result = "administrator";
        }};

        String actualPath = controller.editUser(form, bindingResult, httpSession);
        assertEquals("Admin", actualPath);
    }

    @Test
    public void editError(@Mocked final Form form,
                          @Mocked final BindingResult bindingResult,
                          @Mocked final HttpSession httpSession) {
        new NonStrictExpectations() {{
            bindingResult.hasErrors();
            result = true;
        }};

        String actualPath = controller.editUser(form, bindingResult, httpSession);
        assertEquals("EditUser", actualPath);
    }
}
