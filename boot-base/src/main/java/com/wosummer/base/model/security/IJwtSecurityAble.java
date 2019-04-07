package com.wosummer.base.model.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author yunqisong
 * @date 2018/10/3
 * FOR: 定义Jwt权限和Spring权限验证体系
 */

@SuppressWarnings("ALL")
public interface IJwtSecurityAble extends UserDetails {


  /**
   * 获取传输对象的ID
   *
   * @return
   */
  <T extends Serializable> T getId();

  /**
   * 获取传输对象上次修改密码的时间
   *
   * @return
   */
  Date getLastModifyPasswordTime();

  /**
   * 获取传输对象在本次登录的类型
   *
   * @return
   */
  String getLoginType();

  /**
   * 存储在Token中的额外信息
   *
   * @return
   */
  Map<String, String> extInfo();

}

