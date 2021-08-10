package spring.agency.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import spring.agency.model.entity.Role;
import spring.agency.model.entity.User;
import spring.agency.repository.RoleRepository;
import spring.agency.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class UserDao {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User findById(Long userId) {
        return userRepository.findById(userId).get();
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User save(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setBalance(0.0);
        Role role = roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        return userRepository.save(user);
    }

    public void update(User updatedUser) {
        userRepository.updateBalance(updatedUser.getBalance(), updatedUser.getId());
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
