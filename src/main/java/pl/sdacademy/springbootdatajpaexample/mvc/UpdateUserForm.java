package pl.sdacademy.springbootdatajpaexample.mvc;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateUserForm extends CreateUserForm {
    private Long id;
}
