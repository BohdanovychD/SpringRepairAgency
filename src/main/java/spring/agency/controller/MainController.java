package spring.agency.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import spring.agency.model.entity.User;
import spring.agency.service.UserService;

import javax.validation.Valid;


@Controller
@Slf4j
public class MainController {

    private final UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
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
        log.debug("New user with id: " + user.getId() + " was created" );
        return "register_success";
    }

    @GetMapping("/login")
    public String showSignInForm(Model model, @ModelAttribute User user) {
        model.addAttribute("user", user);
        log.debug("Successful login");
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
