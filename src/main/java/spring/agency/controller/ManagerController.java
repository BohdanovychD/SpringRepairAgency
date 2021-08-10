package spring.agency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.agency.model.entity.Statement;
import spring.agency.model.entity.User;
import spring.agency.service.RoleService;
import spring.agency.service.StatementService;
import spring.agency.service.UserService;


import java.util.List;

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
        List<User> userList = userService.findAllUsers();
        model.addAttribute("userList", userList);
        return "users_list";
    }

    @GetMapping("/masters_list")
    public String viewMasterList(Model model) {
        List<User> masterList = userService.findAllMasters();
        model.addAttribute("masterList", masterList);
        return "masters_list";
    }

    @GetMapping("/statements_list")
    public String viewStatementList(Model model) {
        List<Statement> statementList = statementService.findAllStatements();
        model.addAttribute("statementList", statementList);
        return "check_stm_list";
    }

    @PostMapping(value = "/save/{id}")
    public String saveProduct(@ModelAttribute("user") User user) {
        userService.updateBalance(user);

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
    public String savePrice(@ModelAttribute("statement") Statement statement) {
        statementService.setPrice(statement);

        return "redirect:/manager/statements_list";
    }
}
