package com.wosummer.base.model.enums;

import lombok.Getter;

/**
 * @author yunqisong
 * @date 2018/10/3
 * FOR: 基础返回值枚举
 */

@Getter
public enum BaseResultEnum implements IResultEnum {

  /**
   * 对错误值类型的枚举
   */
  SUCCESS(100, "success"),
  USERNAME_OR_PASSWORD_NOT_CORRECT(101, "username or password not correct"),
  USER_NOT_EXIST(102, "user not exist"),
  UPDATE_FAIL(103, "update fail"),
  PERMISSION_DENY(104, "permission deny"),
  DELETE_FAIL(105, "delete fail"),
  NO_PASSWORD(106, "no password"),
  NO_USERNAME(107, "no username"),
  USERNAME_EXIST(108, "username exist"),
  RECEIVE_FAIL(109, "receive fail"),
  SUBMIT_FAIL(110, "submit fail"),
  NOTHING_FOUND(111, "nothing found"),
  RECEIVE_MISSION_DENY(112, "receive mission deny"),
  NEED_MORE_INFO(113, "need more info"),
  INVITECODE_INVALID(114, "invite code invalid"),
  INFO_NOT_CORRECT(115, "info not correct"),
  REJECT_FAIL(116, "reject fail"),
  NICKNAME_EXIST(117, "nickname exist"),
  INVALID_PARAMETERS(118, "invalid parameters"),
  LOGIN_FAIL(119, "login fail"),
  DATA_ERROR(120, "data error"),
  METHOD_ERROR_POST(121, "http method error : POST"),
  METHOD_ERROR_GET(122, "http method error : GET"),
  JSON_SWITCH_ERROR(123, "json switch error"),
  BAD_REQUEST(400, "bad request");


  BaseResultEnum(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  private Integer code;

  private String message;
}

