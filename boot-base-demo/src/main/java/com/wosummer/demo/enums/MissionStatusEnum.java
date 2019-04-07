package com.wosummer.demo.enums;

import com.wosummer.base.model.enums.BaseResultEnum;
import com.wosummer.base.model.exceptions.LogicException;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: MissionStatusEnum
 */
@Getter
public enum MissionStatusEnum {

  /**
   * 任务状态枚举
   */
  UNCLAIMED("unclaimed", "未领取"),
  UNSUBMITTED("unsubmitted", "未提交"),
  CHECKING("checking", "待审核"),
  TOBEMODIFIED("modify", "待修改"),
  REJECTED("rejected", "已退回"),
  RESOLVED("resolved", "已完成"),
  LOCKED("locked", "已锁定"),
  OUTTIME("outtime", "超时"),
  ;

  private String code;

  private String name;


  MissionStatusEnum(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public static boolean acceptAble(String state) {

    return UNCLAIMED.code.equals(state) || TOBEMODIFIED.code.equals(state);
  }

  public static boolean submitAble(String state) {
    return UNCLAIMED.code.equals(state) || UNSUBMITTED.code.equals(state);
  }

  public static MissionStatusEnum getByState(String state) {

    return Arrays.stream(values()).filter(missionStatusEnum -> missionStatusEnum.code.equals(state)).findFirst().orElseThrow(() -> new LogicException(BaseResultEnum.INFO_NOT_CORRECT));
  }

  public static boolean payAble(String state) {
    return LOCKED.code.equals(state) || RESOLVED.code.equals(state);
  }

}

