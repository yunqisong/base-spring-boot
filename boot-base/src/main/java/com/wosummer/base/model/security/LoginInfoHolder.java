package com.wosummer.base.model.security;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * JKhaled created by yunqisong@foxmail.com 2017/11/21
 * FOR : 线程安全的储存当前用户登录过程中传输的全局关键数据
 *
 * @author yunqisong
 */
@Slf4j
@SuppressWarnings("unchecked")
public class LoginInfoHolder {

  private static final ThreadLocal<String> PASSWORD_HOLDER = new ThreadLocal<>();

  private static final ThreadLocal<String> LOGIN_TYPE_HOLDER = new ThreadLocal<>();

  private static final ThreadLocal<Map<String, String>> CURRENT_USER_EXT_INFO_HOLDER = new ThreadLocal<>();

  static {
    CURRENT_USER_EXT_INFO_HOLDER.remove();
  }

  public static void setSalt(String salt) {
    PASSWORD_HOLDER.remove();
    log.warn("当前用户的salt: {}", salt);
    PASSWORD_HOLDER.set(salt);
  }

  public static String getSalt() {
    return PASSWORD_HOLDER.get();
  }

  public static void setLoginType(String loginType) {
    LOGIN_TYPE_HOLDER.remove();
    log.warn("当前用户的登陆类型: {}", loginType);
    LOGIN_TYPE_HOLDER.set(loginType);
  }

  public static String getLoginType() {

    return LOGIN_TYPE_HOLDER.get();
  }

  public static boolean setExtInfo(Map<String, String> extInfo) {

    if (CollUtil.isNotEmpty(CURRENT_USER_EXT_INFO_HOLDER.get())) {
      CURRENT_USER_EXT_INFO_HOLDER.get().putAll(extInfo);
    } else {
      CURRENT_USER_EXT_INFO_HOLDER.set(Maps.newLinkedHashMap(extInfo));
    }

    return true;
  }

  public static Map<String, String> getExtInfo() {

    return CollUtil.isNotEmpty(CURRENT_USER_EXT_INFO_HOLDER.get()) ?
      CURRENT_USER_EXT_INFO_HOLDER.get() :
      Maps.newLinkedHashMap()
      ;
  }


}
