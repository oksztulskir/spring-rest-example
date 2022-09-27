package pl.sdacademy.springbootdatajpaexample.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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

    @Secured("ROLE_ADMIN")
    @GetMapping(path = "/add")
    public String showCreateForm(ModelMap model) {
        model.addAttribute("user", new CreateUserForm());
        return "create-user";
    }

    @Secured("ROLE_ADMIN")
    @PostMapping(path = "/add")
    public String create(@ModelAttribute("user") CreateUserForm form) {
        userService.create(UserMapper.toEntity(form));
        return "redirect:/mvc/user";
    }

    @GetMapping(path = "/{id}")
    public String showUpdateForm(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("user", userService.find(id));
        return "update-user";
    }

    @PostMapping(path = "/update")
    public String update(@ModelAttribute("user") UpdateUserForm form) {
        userService.update(UserMapper.toEntity(form));
        return "redirect:/mvc/user";
    }
}
