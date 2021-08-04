//package spring.agency.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import spring.agency.model.entity.User;
//import spring.agency.repository.UserRepository;
//
//import java.util.List;
//
//@Controller
//public class ManagerController {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @GetMapping("/manager")
//    public String mainManagerMenu() {
//        return "main_manager";
//    }
//
//    @GetMapping("/manager/users_list")
//    public String viewUserList(Model model) {
//        List<User> userList = userRepository.findAll();
//        model.addAttribute("userList", userList);
//        return "users_list";
//    }
//
//    @GetMapping("/manager/check_stm_list")
//    public String viewStatementList() {
//        return "check_stm_list";
//    }
//
//    @GetMapping("/manager/record_list")
//    public String viewReportList() {
//        return "report_list";
//    }
//
//    @GetMapping("/manager/supplement_balance")
//    public String supplementBalance() {
//        return "supplement_balance";
//    }
//}
