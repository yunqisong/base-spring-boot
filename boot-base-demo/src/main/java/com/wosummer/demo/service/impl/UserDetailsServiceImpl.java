package com.wosummer.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wosummer.base.model.enums.BaseResultEnum;
import com.wosummer.base.model.exceptions.LogicException;
import com.wosummer.demo.entity.User;
import com.wosummer.demo.mapper.UserMapper;
import com.wosummer.demo.model.jwt.JwtUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: UserDetailsServiceImpl
 */
@Slf4j
@SuppressWarnings("ALL")
@Component("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userMapper.selectOne(new QueryWrapper<User>().eq(User.USERNAME, username));

    if (user == null) {
      throw new LogicException(BaseResultEnum.USER_NOT_EXIST);
    }

    return new JwtUser(user);
  }

}

