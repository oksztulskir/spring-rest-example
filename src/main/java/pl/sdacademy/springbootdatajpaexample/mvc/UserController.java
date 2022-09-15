package pl.sdacademy.springbootdatajpaexample.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sdacademy.springbootdatajpaexample.rest.mapper.UserMapper;
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

    @GetMapping(path = "/add")
    public String showCreateForm(ModelMap model) {
        model.addAttribute("user", new CreateUserForm());
        return "create-user";
    }

    @PostMapping(path = "/add")
    public String create(@ModelAttribute("user") CreateUserForm form, ModelMap model) {
        userService.create(UserMapper.toEntity(form));
        model.addAttribute("users", userService.findAll());
        return "redirect:/mvc/user";
    }
}
