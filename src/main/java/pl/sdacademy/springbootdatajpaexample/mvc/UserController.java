package pl.sdacademy.springbootdatajpaexample.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.springbootdatajpaexample.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/mvc/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public String list(ModelMap model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }
}
