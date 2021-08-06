package spring.agency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import spring.agency.dao.RoleDao;
import spring.agency.dao.StatementDao;
import spring.agency.dao.UserDao;
import spring.agency.model.entity.Role;
import spring.agency.model.entity.User;


import java.util.Arrays;

@Controller
public class MainController {

    private UserDao userDao;
    private RoleDao roleDao;
    private StatementDao statementDao;

    @Autowired
    public MainController(UserDao userDao, RoleDao roleDao, StatementDao statementDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.statementDao = statementDao;
    }

    @GetMapping("/")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "sign_up";
    }

    @PostMapping("/register/process")
    public String processRegistration(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setBalance(0.0);
        Role role = roleDao.findByName("USER");
        user.setRoles(Arrays.asList(role));
        userDao.save(user);
        return "register_success";
    }

    @GetMapping("/login")
    public String showSignInForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/login_error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/main_page")
    public String mainUserMenu() {
        return "main_page";
    }
}
