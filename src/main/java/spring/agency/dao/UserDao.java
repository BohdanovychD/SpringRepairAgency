package spring.agency.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import spring.agency.model.entity.Role;
import spring.agency.model.entity.Statement;
import spring.agency.model.entity.User;
import spring.agency.repository.RoleRepository;
import spring.agency.repository.StatementRepository;
import spring.agency.repository.UserRepository;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Component
public class UserDao {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final StatementRepository statementRepository;

    @Autowired
    public UserDao(UserRepository userRepository, RoleRepository roleRepository, StatementRepository statementRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.statementRepository = statementRepository;
    }

    public User findById(Long userId) {
        return userRepository.findById(userId).get();
    }

    public Page<User> findAllUsers(Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10);
        Role role = roleRepository.findByRole("USER");
        Iterable<Long> userId =  userRepository.findAllUserIdByRoleId(role.getId());
        return userRepository.findAllByIdIn(pageable, userId);
    }

    public Page<User> findAllMasters(Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 10);
        Role role = roleRepository.findByRole("MASTER");
        Iterable<Long> userId =  userRepository.findAllUserIdByRoleId(role.getId());
        return userRepository.findAllByIdIn(pageable, userId);
    }

    public User save(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setBalance(0.0);
        Role role = roleRepository.findByRole("USER");
        user.setRoles(Arrays.asList(role));
        return userRepository.save(user);
    }

    public void update(User user) {
        userRepository.updateBalance(user.getBalance(), user.getId());
    }

    public void payBill(Principal principal, Statement statement) {
        String login = principal.getName();
        User user = userRepository.findByLogin(login);
        user.setBalance(user.getBalance() - statement.getPrice());
        statement.setStatus("PAID");
        statementRepository.setPrice(statement.getPrice(), statement.getStatus(), statement.getId());
        userRepository.updateBalance(user.getBalance(), user.getId());
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }




}
