package com.wosummer.demo.enums;

import lombok.Getter;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: EnableEnum
 */
@Getter
public enum EnableEnum {
  /**
   * 是否可用枚举
   */
  YES(1),
  NO(0);

  EnableEnum(Integer code) {
    this.code = code;
  }

  private Integer code;


  public static Boolean isEnable(Integer code) {
    return YES.code.equals(code);
  }

}

