package controller.powermock;

import laba17.domain.User;
import laba17.service.UserDaoService;
import laba17.web.Controller;
import laba17.web.ControllerEdit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.HttpOutputMessage;

import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;

/**
 * Created by redko on 11/4/2014.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Controller.class})
public class DeleteUserControllerTest {

    @InjectMocks
    private Controller controller;

    @Mock
    private UserDaoService userDaoService;

    @Test
    public void testDeleteUser() {
        HttpSession httpSession = mock(HttpSession.class);
        assertEquals("Admin", controller.deleteUser(10, httpSession));
        verify(httpSession).setAttribute(eq("usersList"), any());
        verify(userDaoService).findById(anyLong());
        verify(userDaoService).remove(any(User.class));
    }

}
