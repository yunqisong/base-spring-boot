package com.wosummer.demo.validators;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wosummer.base.kit.SpringKit;
import com.wosummer.demo.annotations.UsernameExist;
import com.wosummer.demo.entity.User;
import com.wosummer.demo.mapper.UserMapper;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: UsernameExistValidator
 */
public class UsernameExistValidator implements ConstraintValidator<UsernameExist, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {

    return SpringKit.getBean(UserMapper.class).selectCount(new QueryWrapper<User>().eq(User.USERNAME, value)) == 0;
  }
}

