package spring.agency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import spring.agency.model.entity.User;
import spring.agency.service.RoleService;
import spring.agency.service.StatementService;
import spring.agency.service.UserService;

import javax.validation.Valid;


@Controller
public class MainController {

    private UserService userService;
    private RoleService roleService;
    private StatementService statementService;

    @Autowired
    public MainController(UserService userService, RoleService roleService, StatementService statementService) {
        this.userService = userService;
        this.roleService = roleService;
        this.statementService = statementService;
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
    public String processRegistration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "sign_up";
        }
        userService.save(user);
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
