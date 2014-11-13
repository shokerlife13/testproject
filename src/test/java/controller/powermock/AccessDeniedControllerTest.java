package controller.powermock;

import laba17.service.UserDaoService;
import laba17.web.Controller;
import laba17.web.ControllerRegistration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by redko on 11/4/2014.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Controller.class, SecurityContextHolder.class})
public class AccessDeniedControllerTest {

    @InjectMocks
    private Controller controller;

    @Mock
    private UserDaoService userDaoService;

    @Test
    public void testAccessDenied() {
        HttpSession httpSession = mock(HttpSession.class);
        PowerMockito.mockStatic(SecurityContextHolder.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        Authentication authentication = new TestingAuthenticationToken("user", "test");
        final ModelAndView model = new ModelAndView();
        model.setViewName("403");
        when(SecurityContextHolder.getContext()).thenReturn(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        assertEquals(model.getViewName(), controller.accessDenied(httpSession).getViewName());
    }
}
