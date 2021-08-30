package spring.agency.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.agency.model.entity.Statement;
import spring.agency.service.StatementService;
import spring.agency.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final StatementService statementService;
    private final UserService userService;

    @Autowired
    public UserController(StatementService statementService, UserService userService) {
        this.statementService = statementService;
        this.userService = userService;
    }

    @GetMapping("/statements_list")
    public String viewStatementList(Model model, Principal principal) {
        return  userStatementListByPage(model, principal, 1);
    }

    @GetMapping("/statements_list/page/{pageNumber}")
    public String userStatementListByPage(Model model, Principal principal, @PathVariable("pageNumber") Integer currentPage) {
        Page<Statement> page = statementService.findByUserId(principal, currentPage);

        model.addAttribute("currentPage", page.getNumber());
        model.addAttribute("currentPage", page.getNumber());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("size", page.getSize());
        model.addAttribute("statementList", page.getContent());
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
        log.debug("New statement with id: " + statement.getId() + " is created " );
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
        log.debug("User left the comment for statement with id: " + statement.getId());

        return "redirect:/user/statements_list";
    }
}
