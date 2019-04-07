package com.wosummer.demo.enums;

import lombok.Getter;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: PayStatusEnum
 */
@Getter
public enum PayStatusEnum {

  /**
   * 是否付过款
   */
  NOT_PAY(0),
  PAID(1);

  PayStatusEnum(Integer code) {
    this.code = code;
  }

  private Integer code;


  public static Boolean isPaid(Integer code) {
    return PAID.code.equals(code);
  }

}

