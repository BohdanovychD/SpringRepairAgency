package spring.agency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.agency.model.entity.Statement;
import spring.agency.model.entity.User;
import spring.agency.repository.StatementRepository;
import spring.agency.repository.UserRepository;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StatementRepository statementRepository;

    @GetMapping("/users_list")
    public String viewUserList(Model model) {
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);
        return "users_list";
    }

    @GetMapping("/statement_list")
    public String viewStatementList(Model model) {
        List<Statement> statementList = statementRepository.findAll();
        model.addAttribute("statementList", statementList);
        return "check_stm_list";
    }
}
