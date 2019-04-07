package com.wosummer.demo.model.jwt;

import cn.hutool.core.date.DateUtil;
import com.wosummer.base.model.security.IJwtSecurityAble;
import com.wosummer.base.model.security.LoginInfoHolder;
import com.wosummer.demo.entity.User;
import com.wosummer.demo.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: JwtUser
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class JwtUser implements IJwtSecurityAble {

  private User user;

  @Override
  public <T extends Serializable> T getId() {
    return (T) user.getId();
  }

  @Override
  public Date getLastModifyPasswordTime() {

    return DateUtil.offsetDay(DateUtil.date(), -1);
  }

  @Override
  public String getLoginType() {

    return String.valueOf(user.getRole());
  }

  @Override
  public Map<String, String> extInfo() {

    return LoginInfoHolder.getExtInfo();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    // TODO: 当前单一角色作为权限判断依据,后续需要拓展角色权限系统
    return Collections.singleton(new SimpleGrantedAuthority(RoleEnum.getByCode(user.getRole()).getMark()));
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return isEnabled();
  }

  @Override
  public boolean isAccountNonLocked() {
    return isEnabled();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return isEnabled();
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}

