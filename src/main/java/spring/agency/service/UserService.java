package spring.agency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import spring.agency.dao.UserDao;
import spring.agency.model.details.UserDetailsImpl;
import spring.agency.model.entity.User;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findById(Long userId) {
        return userDao.findById(userId);
    }

    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    public User save(User user) {
        return userDao.save(user);
    }

    public void updateBalance(User updatedUser) {
        userDao.update(updatedUser);
    }

    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }


}
