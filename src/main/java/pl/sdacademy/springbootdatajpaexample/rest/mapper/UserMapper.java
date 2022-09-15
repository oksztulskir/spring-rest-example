package pl.sdacademy.springbootdatajpaexample.rest.mapper;

import pl.sdacademy.springbootdatajpaexample.entity.User;
import pl.sdacademy.springbootdatajpaexample.mvc.CreateUserForm;
import pl.sdacademy.springbootdatajpaexample.rest.dto.UserDto;

public class UserMapper {
    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .login(user.getLogin())
                .build();
    }

    public static User toEntity(CreateUserForm form) {
        return User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .login(form.getLogin())
                .password(form.getPassword())
                .build();
    }
}
