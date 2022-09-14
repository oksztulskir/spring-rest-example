package pl.sdacademy.springbootdatajpaexample.rest.mapper;

import pl.sdacademy.springbootdatajpaexample.entity.User;
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
}
