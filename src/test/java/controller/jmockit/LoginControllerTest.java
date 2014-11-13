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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Test class for Controller(login)
 * Created by redko on 10/6/2014.
 */
@RunWith(JMockit.class)
public class LoginControllerTest {

    @Tested
    private Controller controller;

    @Injectable
    private UserDaoService userDaoService;

    @Test
    public void testLoginAdminRole(@Mocked final HttpSession httpSession) {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ADMINISTRATOR"));
        new NonStrictExpectations() {{
            Authentication authentication = new TestingAuthenticationToken("user", "test", authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }};
        assertEquals("Admin", controller.login(httpSession));
    }

    @Test
    public void testLoginUserRole(@Mocked final HttpSession httpSession) {
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        new NonStrictExpectations() {{
            Authentication authentication = new TestingAuthenticationToken("user", "test", authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }};
        assertEquals("User", controller.login(httpSession));
    }

    @Test
    public void testLoginUndefinedRole(@Mocked final HttpSession httpSession) {
        new NonStrictExpectations() {{
            Authentication authentication = new TestingAuthenticationToken("user", "test");
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }};
        assertEquals("Login", controller.login(httpSession));
    }

}
