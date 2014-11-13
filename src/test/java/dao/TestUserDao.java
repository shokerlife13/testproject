package dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import laba17.domain.Role;
import laba17.domain.User;
import laba17.repository.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Test class for table of users
 * Created by redko on 10/3/2014.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:webtest-config.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
public class TestUserDao {

    @Autowired
    private UserDao userDao;

    @Test
    @DatabaseSetup("/data/user/data.xml")
    @ExpectedDatabase("/data/user/data-after-create.xml")
    public void testCreate() {
        userDao.create(new User(new Role(2, "User"), "John", "j_123i", "john@rambler.ru", "John",
                "Eze", Date.valueOf("1992-12-25")));
    }

    @Test
    @DatabaseSetup("/data/user/data.xml")
    @ExpectedDatabase("/data/user/data-after-update.xml")
    public void testUpdate() {
        userDao.update(new User(2, new Role(2, "User"), "DemidS", "98765g", "demid@mail.ru",
                "Demid", "Ranenko", Date.valueOf("1993-11-23")));
    }

    @Test
    @DatabaseSetup("/data/user/data.xml")
    @ExpectedDatabase("/data/user/data-after-remove.xml")
    public void testRemove() {
        userDao.remove(new User(2, new Role(1, "Administrator"), "Demid", "98765g", "demid@mail.ru",
                "Demid", "Ranenko", Date.valueOf("1993-11-23")));
    }

    @Test
    @DatabaseSetup("/data/role/data.xml")
    public void testFindAll() {
        List<User> actualUserList = new ArrayList<User>();
        actualUserList.add(new User(1, new Role(1, "Administrator"), "Duke_Nukem", "12345", "dn@gmail.com",
                "Duke", "Nukem", Date.valueOf("1945-07-15")));
        actualUserList.add(new User(2, new Role(1, "Administrator"), "Demid", "98765g", "demid@mail.ru",
                "Demid", "Ranenko", Date.valueOf("1993-11-23")));
        List<User> expectedUserList = userDao.findAll();
        assertEquals(actualUserList.toString(), expectedUserList.toString());
    }

    @Test
    @DatabaseSetup("/data/role/data.xml")
    public void testFindByLogin() {
        User user = userDao.findByLogin("Demid");
        assertNotNull(user);
    }

    @Test
    @DatabaseSetup("/data/role/data.xml")
    public void testFindByEmail() {
        User user = userDao.findByEmail("demid@mail.ru");
        assertNotNull(user);
    }

    @Test
    @DatabaseSetup("/data/role/data.xml")
    public void testFindById() {
        User user = userDao.findById(1);
        assertNotNull(user);
    }
}
