package dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import laba17.domain.Role;
import laba17.repository.RoleDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import static junit.framework.TestCase.assertNotNull;

/**
 * Test class for table of roles
 * Created by redko on 10/3/2014.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:webtest-config.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
public class TestRoleDao {

    @Autowired
    private RoleDao roleDao;

    @Test
    @DatabaseSetup("/data/role/data.xml")
    @ExpectedDatabase("/data/role/data-after-create.xml")
    public void testCreate() {
        roleDao.create(new Role(3, "Moderator"));
    }

    @Test
    @DatabaseSetup("/data/role/data.xml")
    @ExpectedDatabase("/data/role/data-after-update.xml")
    public void testUpdate() {
        roleDao.update(new Role(1, "Admin"));
    }

    @Test
    @DatabaseSetup("/data/role/data.xml")
    @ExpectedDatabase("/data/role/data-after-remove.xml")
    public void testRemove() {
        roleDao.remove(new Role(2, "User"));
    }

    @Test
    @DatabaseSetup("/data/role/data.xml")
    public void testFindByName() {
        Role role = roleDao.findByName("User");
        assertNotNull(role);
    }
}
