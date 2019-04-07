package com.wosummer.base.model;

import com.wosummer.base.model.enums.BaseResultEnum;
import com.wosummer.base.model.enums.IResultEnum;
import com.wosummer.base.model.exceptions.LogicException;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author yunqisong
 * @date 2018/10/3
 * FOR: 返回Bean基础分装
 */

@Data
@Accessors(chain = true)
@SuppressWarnings("ALL")
public class Result<T> {

  private String message;

  private Integer code;

  private T data;

  /**
   * 返回成功值
   *
   * @param data
   * @param <T>
   * @return
   */
  public static <T> Result<T> success(T data) {

    return new Result()
      .setCode(BaseResultEnum.SUCCESS.getCode())
      .setMessage(BaseResultEnum.SUCCESS.getMessage())
      .setData(data)
      ;
  }

  /**
   * 返回数据验证错误信息
   *
   * @param msg
   * @param <T>
   * @return
   */
  public static <T> Result<T> fail(String msg) {

    return fail(BaseResultEnum.INVITECODE_INVALID.getCode(), msg);
  }

  /**
   * 返回失败值
   *
   * @param code
   * @param msg
   * @param <T>
   * @return
   */
  public static <T> Result<T> fail(Integer code, String msg) {

    return new Result().setCode(code).setMessage(msg);
  }

  /**
   * 返回失败值枚举
   *
   * @param resultEnum
   * @param <T>
   * @return
   */
  public static <T> Result<T> fail(IResultEnum resultEnum) {

    return fail(resultEnum.getCode(), resultEnum.getMessage());
  }

  /**
   * 返回失败值枚举和信息
   *
   * @param resultEnum
   * @param msg
   * @param <T>
   * @return
   */
  public static <T> Result<T> fail(IResultEnum resultEnum, String msg) {

    return fail(resultEnum.getCode(), msg);
  }

  /**
   * 通过异常返回错误
   *
   * @param e
   * @param <T>
   * @return
   */
  public static <T> Result<T> fail(LogicException e) {

    return fail(e.getCode(), e.getMessage());
  }


}

