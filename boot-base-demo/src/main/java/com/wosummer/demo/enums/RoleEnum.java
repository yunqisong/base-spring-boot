package com.wosummer.demo.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: RoleEnum
 */
@Getter
public enum RoleEnum {
  /**
   * 角色枚举
   */
  NONE(-1, "", "错误角色"),
  ADMIN(1, "ROLE_ADMIN", "管理员"),
  USER(0, "ROLE_USER", "普通用户");

  private Integer code;

  private String mark;

  private String info;

  RoleEnum(Integer code, String mark, String info) {
    this.code = code;
    this.mark = mark;
    this.info = info;
  }

  public static RoleEnum getByCode(Integer role) {

    return Arrays.stream(values()).filter(roleEnum -> roleEnum.getCode().equals(role)).findFirst().orElse(NONE);
  }
}
