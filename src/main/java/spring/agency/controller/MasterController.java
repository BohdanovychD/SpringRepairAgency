package spring.agency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.agency.model.entity.Statement;
import spring.agency.service.StatementService;
import spring.agency.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/master")
public class MasterController {

    private final StatementService statementService;
    private final UserService userService;

    @Autowired
    public MasterController(StatementService statementService, UserService userService) {
        this.statementService = statementService;
        this.userService = userService;
    }

    @GetMapping("/statements_list")
    public String viewStatementList(Model model) {
        List<Statement> statementList = statementService.findAllStatements();
        model.addAttribute("statementList", statementList);
        return "check_master_stm";
    }

    @GetMapping("/statements_list/{id}/take")
    public String takeStatement(@PathVariable(name = "id") Long id) {
        Statement statement = statementService.findById(id);
        statementService.takeStatement(statement);
        return "redirect:/master/statements_list";
    }

    @GetMapping("/statements_list/{id}/done")
    public String finishStatement(@PathVariable(name = "id") Long id) {
        Statement statement = statementService.findById(id);
        statementService.finishStatement(statement);
        return "redirect:/master/statements_list";
    }
}
