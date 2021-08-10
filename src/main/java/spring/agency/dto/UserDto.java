package spring.agency.dto;

import spring.agency.model.entity.Role;
import spring.agency.model.entity.Statement;

import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private Long id;
    private String login;
    private String password;
    private String name;
    private Double balance;
    private List<Role> roles = new ArrayList<>();
    List<Statement> statements = new ArrayList<>();

}
