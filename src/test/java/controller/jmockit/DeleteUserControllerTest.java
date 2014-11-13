package controller.jmockit;

import laba17.domain.User;
import laba17.service.UserDaoService;
import laba17.web.Controller;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertEquals;

/**
 * Test class for Controller(delete)
 * Created by redko on 10/7/2014.
 */
@RunWith(JMockit.class)
public class DeleteUserControllerTest {

    @Tested
    private Controller controller;

    @Injectable
    private UserDaoService userDaoService;

    @Test
    public void testDeleteUser(@Mocked final HttpSession httpSession) {
        assertEquals("Admin", controller.deleteUser(10, httpSession));
        new Verifications() {{
            userDaoService.remove((User) any);
            httpSession.setAttribute("usersList", any);
        }};

    }
}
