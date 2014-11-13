package controller.powermock;

import laba17.service.UserDaoService;
import laba17.web.Controller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by redko on 11/3/2014.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Controller.class, SecurityContextHolder.class})
public class LoginControllerTest {

    @InjectMocks
    private Controller controller;

    @Mock
    private UserDaoService userDaoService;

    @Test
    public void testLoginAdminRole() {
        HttpSession httpSession = mock(HttpSession.class);
        PowerMockito.mockStatic(SecurityContextHolder.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ADMINISTRATOR"));
        Authentication authentication = new TestingAuthenticationToken("user", "test", authorities);
        when(SecurityContextHolder.getContext()).thenReturn(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        assertEquals("Admin", controller.login(httpSession));
    }

    @Test
    public void testLoginUserRole() {
        HttpSession httpSession = mock(HttpSession.class);
        PowerMockito.mockStatic(SecurityContextHolder.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        Authentication authentication = new TestingAuthenticationToken("user", "test", authorities);
        when(SecurityContextHolder.getContext()).thenReturn(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        assertEquals("User", controller.login(httpSession));
    }

    @Test
    public void testLoginUndefinedRole() {
        HttpSession httpSession = mock(HttpSession.class);
        PowerMockito.mockStatic(SecurityContextHolder.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = new TestingAuthenticationToken("user", "test");
        when(SecurityContextHolder.getContext()).thenReturn(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        assertEquals("Login", controller.login(httpSession));
    }

}
