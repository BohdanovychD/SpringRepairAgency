package spring.agency.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {



    @GetMapping("/user/create_smt")
    public String createStatement() {
        return "create_smt";
    }

    @GetMapping("/user/pay_bill")
    public String payBill() {
        return "pay_bill";
    }
}
