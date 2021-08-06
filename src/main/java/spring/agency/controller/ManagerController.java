package spring.agency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.agency.dao.StatementDao;
import spring.agency.dao.UserDao;
import spring.agency.model.entity.Statement;
import spring.agency.model.entity.User;


import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    private UserDao userDao;
    private StatementDao statementDao;

    @Autowired
    public ManagerController(UserDao userDao, StatementDao statementDao) {
        this.userDao = userDao;
        this.statementDao = statementDao;
    }


    @GetMapping("/users_list")
    public String viewUserList(Model model) {
        List<User> userList = userDao.findAllUsers();
        model.addAttribute("userList", userList);
        return "users_list";
    }


    @GetMapping("/statement_list")
    public String viewStatementList(Model model) {
        List<Statement> statementList = statementDao.findAllStatements();
        model.addAttribute("statementList", statementList);
        return "check_stm_list";
    }

    @PostMapping(value = "/save/{id}")
    public String saveProduct(@ModelAttribute("user") User user, @PathVariable(name = "id") Long id) {
        userDao.update(user);

        return "redirect:/manager/users_list";
    }

    @GetMapping("/users_list/{id}/add_money")
    public ModelAndView giveMoney(@PathVariable(name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("supplement_balance");

        User user = userDao.findById(id);
        modelAndView.addObject("user", user);

        return modelAndView;

    }
}
