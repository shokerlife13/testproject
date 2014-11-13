package rest;

import laba17.domain.Role;
import laba17.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.*;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.sql.Date;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

/**
 * Test class for UserREST (RESTful web-service)
 * Created by Rostislav on 23.10.2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:webtest-config.xml"})
public class TestUserREST {

    private final String restUrl = "http://10.10.32.109:8080/rest/user/";

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void findAllTest() {
        HttpEntity request = new HttpEntity(createHeaders("admin", "admin"));
        ResponseEntity<User[]> responseEntity = restTemplate
                .exchange(restUrl, HttpMethod.GET, request,
                        User[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(1, responseEntity.getBody().length);
    }

    @Test
    public void findByIdTest() {
        HttpEntity request = new HttpEntity(createHeaders("admin", "admin"));
        ResponseEntity<User> responseEntity = restTemplate.exchange(restUrl + "findById/{id}", HttpMethod.GET, request,
                User.class, 1);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("admin", responseEntity.getBody().getLogin());
    }

    @Test
    public void findByLoginTest() {
        HttpEntity request = new HttpEntity(createHeaders("admin", "admin"));
        ResponseEntity<User> responseEntity = restTemplate.exchange(restUrl + "findByLogin/{login}", HttpMethod.GET, request,
                User.class, "admin");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("admin", responseEntity.getBody().getLogin());
    }


    @Test
    public void testCreateRemoveUser() {
        User tempUser = new User(new Role(2, "User"), "test", "test", "test", "test", "test", Date.valueOf("1992-08-10"));
        HttpEntity<User> request = new HttpEntity<User>(tempUser, createHeaders("admin", "admin"));
        ResponseEntity<User> responseEntity;
        restTemplate.exchange(restUrl, HttpMethod.POST, request, User.class);
        responseEntity = restTemplate.exchange(restUrl + "findByLogin/{login}", HttpMethod.GET, request,
                User.class, "test");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("test", responseEntity.getBody().getLogin());
        restTemplate.exchange(restUrl + "/{id}", HttpMethod.DELETE, request, User.class, responseEntity.getBody().getIdUser());
    }

    /**
     * Method creates http headers for users
     *
     * @param username username
     * @param password password
     * @return HttpHeaders
     */
    private HttpHeaders createHeaders(final String username, final String password) {
        String authData = username + ":" + password;
        byte[] authDataBytes = authData.getBytes();
        byte[] base64AuthDataBytes = Base64.encode(authDataBytes);
        String base64AuthData = new String(base64AuthDataBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Basic " + base64AuthData);
        return headers;
    }

}
