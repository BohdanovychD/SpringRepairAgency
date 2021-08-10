package spring.agency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.agency.model.entity.Statement;
import spring.agency.model.entity.User;
import spring.agency.service.StatementService;
import spring.agency.service.UserDetailsServiceImpl;
import spring.agency.service.UserService;


import java.security.Principal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    private StatementService statementService;
    private UserService userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserController(StatementService statementService, UserService userService) {
        this.statementService = statementService;
        this.userService = userService;
    }

    @GetMapping("/statements_list")
    public String viewStatementList(Model model, Principal principal) {
        List<Statement> statementList = statementService.findByUserId(principal);
        model.addAttribute("statementList", statementList);
        return "check_user_stm";
    }

    @GetMapping("/create_statement")
    public String createStatement(Model model) {
        model.addAttribute("statement", new Statement());
        return "create_stm";
    }

    @PostMapping("/create_statement/success")
    public String successStatement(Statement statement, Principal principal) {
        statementService.save(statement, principal);
        return "stm_success";
    }
}
