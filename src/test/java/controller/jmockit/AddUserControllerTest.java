package controller.jmockit;

import laba17.service.RoleDaoService;
import laba17.service.UserDaoService;
import laba17.validation.Form;
import laba17.web.Controller;
import mockit.*;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertEquals;

/**
 * Test class for Controller(add)
 * Created by redko on 10/6/2014.
 */
@RunWith(JMockit.class)
public class AddUserControllerTest {

    @Tested
    private Controller controller;

    @Injectable
    private UserDaoService userDaoService;

    @Injectable
    private RoleDaoService roleDaoService;

    @Mocked
    Model model;

    @Test
    public void testGoToAddPage() {
        assertEquals("AddUser", controller.goToAddPage(model));
        new Verifications() {{
            model.addAttribute("validationForm", (Form) any);
        }};
    }

    @Test
    public void testAddUserSuccessfully(@Mocked final Form form,
                                        @Mocked final BindingResult bindingResult,
                                        @Mocked final HttpSession httpSession) {
        new NonStrictExpectations() {{
            form.getBirthday();
            result = "1995-03-13";
            form.getRole();
            result = "user";
        }};

        String actualPath = controller.addUser(form, bindingResult, httpSession);
        assertEquals("Admin", actualPath);
    }

    @Test
    public void testAddAdminSuccessfully(@Mocked final Form form,
                                         @Mocked final BindingResult bindingResult,
                                         @Mocked final HttpSession httpSession) {
        new NonStrictExpectations() {{
            form.getBirthday();
            result = "1995-03-13";
            form.getRole();
            result = "administrator";
        }};

        String actualPath = controller.addUser(form, bindingResult, httpSession);
        assertEquals("Admin", actualPath);
    }

    @Test
    public void testAddUserError(@Mocked final Form form,
                                 @Mocked final BindingResult bindingResult,
                                 @Mocked final HttpSession httpSession) {
        new NonStrictExpectations() {{
            bindingResult.hasErrors();
            result = true;
        }};

        String actualPath = controller.addUser(form, bindingResult, httpSession);
        assertEquals("AddUser", actualPath);
    }

}
