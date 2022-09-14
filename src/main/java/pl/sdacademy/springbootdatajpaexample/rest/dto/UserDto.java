package pl.sdacademy.springbootdatajpaexample.rest.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {
    private long id;

    private String firstName;

    private String lastName;

    private String login;
}
