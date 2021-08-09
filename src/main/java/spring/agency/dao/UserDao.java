package spring.agency.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.agency.model.entity.User;
import spring.agency.repository.UserRepository;

import java.util.List;

@Component
public class UserDao {

    @Autowired
    private UserRepository userRepository;

    public User findById(Long userId) {
        return userRepository.findById(userId).get();
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void update(User updatedUser) {
        userRepository.updateBalance(updatedUser.getBalance(), updatedUser.getId());
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
}
