package spring.agency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String viewStatementList(Model model, Principal principal) {
        return masterStatementListByPage(model, 1, "id", "asc", principal);
    }

    @GetMapping("/statements_list/page/{pageNumber}")
    public String masterStatementListByPage(Model model, @PathVariable("pageNumber") Integer currentPage,
                                            @Param("sortField") String sortField,
                                            @Param("sortDir") String sortDir,
                                            Principal principal) {
        Page<Statement> page = statementService.findAllStatementsForMaster(currentPage, sortField, sortDir, principal);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("size", page.getSize());
        model.addAttribute("statementList", page.getContent());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "check_master_stm";
    }

    @GetMapping("/statements_list/{id}/take")
    public String takeStatement(@PathVariable(name = "id") Long id, Principal principal) {
        Statement statement = statementService.findById(id);
        statementService.takeStatement(statement, principal);
        return "redirect:/master/statements_list";
    }

    @GetMapping("/statements_list/{id}/done")
    public String finishStatement(@PathVariable(name = "id") Long id, Principal principal) {
        Statement statement = statementService.findById(id);
        statementService.finishStatement(statement, principal);
        return "redirect:/master/statements_list";
    }
}
