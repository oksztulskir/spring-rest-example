package pl.sdacademy.springbootdatajpaexample.mvc;

import lombok.Data;

@Data
public class CreateUserForm {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
}
