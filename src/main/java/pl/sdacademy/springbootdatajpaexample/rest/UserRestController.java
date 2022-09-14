package pl.sdacademy.springbootdatajpaexample.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sdacademy.springbootdatajpaexample.entity.User;
import pl.sdacademy.springbootdatajpaexample.rest.dto.UserDto;
import pl.sdacademy.springbootdatajpaexample.rest.mapper.UserMapper;
import pl.sdacademy.springbootdatajpaexample.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import static pl.sdacademy.springbootdatajpaexample.rest.mapper.UserMapper.toDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user") // http://localhost:8080/user
public class UserRestController {
    private final UserService userService;

    // GET http://localhost:8080/user/101
    @GetMapping("/{id}")
    public UserDto find(@PathVariable("id") long id) {
        return toDto(userService.find(id));
    }

    // GET http://localhost:8080/user
    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll().stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    // POST http://localhost:8080/user
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    // DELETE http://localhost:8080/user/101
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        userService.delete(id);
    }

    // PUT http://localhost:8080/user
    @PutMapping
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    // PATCH http://localhost:8080/user
    @PatchMapping
    public User patchUpdate(@RequestBody User user) {
        return userService.patchUpdate(user);
    }

    // GET http://localhost:8080/user/page
    @GetMapping("/page")
    public Page<User> findAll(@PageableDefault(size = Integer.MAX_VALUE) Pageable pageable) {
        return userService.getPage(pageable);
    }
}
