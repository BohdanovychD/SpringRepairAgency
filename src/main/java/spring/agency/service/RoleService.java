package spring.agency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.agency.dao.RoleDao;
import spring.agency.model.entity.Role;
import spring.agency.model.entity.User;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role findByName(String name) {
        return roleDao.findByName(name);
    }

    public void updateToMaster(User user) {
        roleDao.updateToMaster(user);
    }
}
