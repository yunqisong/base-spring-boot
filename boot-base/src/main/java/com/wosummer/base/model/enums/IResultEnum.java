package com.wosummer.base.model.enums;

/**
 *
 * @author yunqisong
 * @date 2018/10/3
 * FOR: 错误返回值枚举
 */

public interface IResultEnum {

  /**
   * 获取Code
   *
   * @return 提供错误返回值的Code
   */
  Integer getCode();

  /**
   * 返回值的Message
   *
   * @return 提供错误返回值的Message
   */
  String getMessage();
}

