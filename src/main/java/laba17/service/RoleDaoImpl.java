package laba17.service;

import laba17.domain.Role;
import laba17.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class service to work with the table Role of database
 * Created by redko on 9/29/2014.
 */
@Service
public class RoleDaoImpl implements RoleDaoService {

    @Autowired
    private RoleDao roleDao;

    @Transactional
    public void create(Role role) {
        roleDao.create(role);
    }

    @Transactional
    public void update(Role role) {
        roleDao.update(role);
    }

    @Transactional
    public void remove(Role role) {
        roleDao.remove(role);
    }

    @Transactional
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }
}
