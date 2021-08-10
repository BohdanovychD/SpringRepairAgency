package spring.agency.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.agency.dao.UserDao;
import spring.agency.model.entity.Statement;
import spring.agency.model.entity.User;

import java.security.Principal;
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

    public List<User> findAllMasters() {
        return userDao.findAllMasters();
    }

    public User save(User user) {
        return userDao.save(user);
    }

    public void updateBalance(User user) {
        userDao.update(user);
    }

    public void payBill(Principal principal, Statement statement) {
        userDao.payBill(principal, statement);
    }

    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }


}
