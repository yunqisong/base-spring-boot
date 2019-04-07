package com.wosummer.demo.annotations;

import com.wosummer.demo.validators.UsernameExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: UsernameExist
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameExistValidator.class)
public @interface UsernameExist {

  String message() default "用户名已存在";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}

