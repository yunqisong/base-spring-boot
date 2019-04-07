package com.wosummer.demo.enums;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: ArticleStatusEnum
 */
@Getter
public enum ArticleStatusEnum {

  /**
   * 文章状态枚举
   */
  EDITABLE("editable", "可编辑"),
  READONLY("readonly", "不可编辑"),
  ;

  private String code;

  private String name;


  ArticleStatusEnum(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public static boolean editable(String state) {

    return EDITABLE.code.equals(state);
  }
}

