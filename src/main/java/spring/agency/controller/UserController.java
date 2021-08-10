package spring.agency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.agency.model.entity.Statement;
import spring.agency.service.StatementService;
import spring.agency.service.UserService;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private StatementService statementService;
    private UserService userService;

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

    @GetMapping("/statements_list/{id}/pay")
    public String payBill(@PathVariable(name = "id") Long id, Principal principal) {
        Statement statement = statementService.findById(id);
        userService.payBill(principal, statement);
        return "redirect:/user/statements_list";
    }

    @GetMapping("/statements_list/{id}/cancel")
    public String cancelStatement(@PathVariable(name = "id") Long id) {
        Statement statement = statementService.findById(id);
        statementService.cancelStatement(statement);
        return "redirect:/user/statements_list";
    }

    @GetMapping("/statements_list/{id}/leave_comment")
    public ModelAndView setPrice(@PathVariable(name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("leave_comment");

        Statement statement = statementService.findById(id);
        modelAndView.addObject("statement", statement);

        return modelAndView;
    }

    @PostMapping(value = "/leave_comment/{id}")
    public String savePrice(@ModelAttribute("statement") Statement statement) {
        statementService.leaveComment(statement);

        return "redirect:/user/statements_list";
    }
}
