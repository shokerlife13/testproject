package controller.jmockit;

import laba17.service.UserDaoService;
import laba17.web.Controller;
import mockit.Injectable;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertEquals;

/**
 * Test class for Controller(accessDenied)
 * Created by redko on 10/6/2014.
 */
@RunWith(JMockit.class)
public class AccessDeniedControllerTest {

    @Tested
    private Controller controller;

    @Injectable
    private UserDaoService userDaoService;

    @Test
    public void testAccessDenied(@Mocked final HttpSession session) {
        final ModelAndView model = new ModelAndView();
        new NonStrictExpectations() {{
            Authentication authentication = new TestingAuthenticationToken("user", "test");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            model.addObject("user", authentication.getName());
        }};
        model.setViewName("403");
        assertEquals(model.getViewName(), controller.accessDenied(session).getViewName());
    }
}
