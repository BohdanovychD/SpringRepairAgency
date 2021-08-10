package spring.agency.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.agency.model.entity.Role;
import spring.agency.model.entity.User;
import spring.agency.repository.RoleRepository;

@Component
public class RoleDao {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(String name) {
        return roleRepository.findByRole(name);
    }

    public void updateToMaster(User user) {
        Role role = roleRepository.findByRole("MASTER");
        roleRepository.updateRole(role.getId(), user.getId());
    }
}
