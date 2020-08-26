package com.example.myblog.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class AuthorValidator implements ConstraintValidator<Author, String> {

    private final List<String> VALID_AUTHORS = Arrays.asList("meimeihan", "leili");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //判断验证是否通过的业务逻辑
        return VALID_AUTHORS.contains(value);
    }
}
