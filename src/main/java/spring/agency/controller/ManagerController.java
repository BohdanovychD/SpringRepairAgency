package spring.agency.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.agency.model.entity.Statement;
import spring.agency.model.entity.User;
import spring.agency.service.RoleService;
import spring.agency.service.StatementService;
import spring.agency.service.UserService;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/manager")
public class ManagerController {

    private UserService userService;
    private StatementService statementService;
    private RoleService roleService;

    @Autowired
    public ManagerController(UserService userService, StatementService statementService, RoleService roleService) {
        this.userService = userService;
        this.statementService = statementService;
        this.roleService = roleService;
    }


    @GetMapping("/users_list")
    public String viewUserList(Model model) {
        return userListByPage(model, 1);
    }

    @GetMapping("/users_list/page/{pageNumber}")
    public String userListByPage(Model model, @PathVariable("pageNumber") Integer currentPage) {
        Page<User> page = userService.findAllUsers(currentPage);

        model.addAttribute("currentPage", page.getNumber());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("size", page.getSize());
        model.addAttribute("userList", page.getContent());
        return "users_list";
    }

    @GetMapping("/masters_list")
    public String viewMasterList(Model model) {
        return mastersListByPage(model, 1);
    }

    @GetMapping("/masters_list/page/{pageNumber}")
    public String mastersListByPage(Model model, @PathVariable("pageNumber") Integer currentPage) {
        Page<User> page = userService.findAllMasters(currentPage);

        model.addAttribute("currentPage", page.getNumber());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("size", page.getSize());
        model.addAttribute("masterList", page.getContent());
        return "masters_list";
    }

    @GetMapping("/statements_list")
    public String viewStatementList(Model model) {
        return statementListByPage(model, 1, "id", "asc");
    }

    @GetMapping("/statements_list/page/{pageNumber}")
    public String statementListByPage(Model model, @PathVariable("pageNumber") Integer currentPage,
                                      @Param("sortField") String sortField,
                                      @Param("sortDir") String sortDir) {
        Page<Statement> page = statementService.findAllUncompletedStatements(currentPage, sortField, sortDir);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("size", page.getSize());
        model.addAttribute("statementList", page.getContent());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "check_stm_list";
    }

    @GetMapping("/reports_list")
    public String viewReportList(Model model) {
        return reportListByPage(model, 1, "id", "asc");
    }

    @GetMapping("/reports_list/page/{pageNumber}")
    public String reportListByPage(Model model, @PathVariable("pageNumber") Integer currentPage,
                                      @Param("sortField") String sortField,
                                      @Param("sortDir") String sortDir) {
        Page<Statement> page = statementService.findAllCompletedStatements(currentPage, sortField, sortDir);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("size", page.getSize());
        model.addAttribute("statementList", page.getContent());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        return "reports_list";
    }

    @PostMapping(value = "/save/{id}")
    public String saveProduct(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "supplement_balance";
        }
        userService.updateBalance(user);
        log.debug("Manager supplement the balance for user with id: " + user.getId());

        return "redirect:/manager/users_list";
    }

    @GetMapping("/users_list/{id}/add_money")
    public ModelAndView giveMoney(@PathVariable(name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("supplement_balance");

        User user = userService.findById(id);
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @GetMapping("/users_list/{id}/appoint_master")
    public String appointMaster(@ModelAttribute User user) {
        roleService.updateToMaster(user);
        log.debug("User with id " + user.getId() + " was appoint as master");
        return "redirect:/manager/users_list";
    }

    @GetMapping("/statements_list/{id}/set_price")
    public ModelAndView setPrice(@PathVariable(name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("set_price");

        Statement statement = statementService.findById(id);
        modelAndView.addObject("statement", statement);

        return modelAndView;
    }

    @PostMapping(value = "/set/{id}")
    public String savePrice(@ModelAttribute("statement") @Valid Statement statement, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "set_price";
        }
        statementService.setPrice(statement);
        log.debug("Manager set the price for statement with id:" + statement.getId());

        return "redirect:/manager/statements_list";
    }
}
