package com.wosummer.base.controller;


import com.wosummer.base.model.Result;
import com.wosummer.base.model.enums.IResultEnum;
import com.wosummer.base.model.exceptions.LogicException;
import lombok.extern.slf4j.Slf4j;

/**
 * BaseController created by yunqisong 2017/11/21
 * FOR : BaseController基类
 *
 * @author yunqisong
 */

@Slf4j
@SuppressWarnings("ALL")
public abstract class BaseController {


  /**
   * 基本返回成功信息
   *
   * @return
   */
  protected <T> Result success(T data) {

    log.debug("Data : {}", data);
    return Result.success(data);
  }

  /**
   * 缺省返回成功信息
   *
   * @return
   */
  protected Result success() {

    return success("成功");
  }

  /**
   * 错误返回
   *
   * @param code
   * @param msg
   * @return
   */
  protected Result fail(Integer code, String msg) {

    log.debug("Error code: {}, msg: {}", code, msg);
    return Result.fail(code, msg);
  }

  /**
   * 枚举直接构造错误
   *
   * @param resultEnum
   * @return
   */
  protected Result fail(IResultEnum resultEnum) {

    return fail(resultEnum.getCode(), resultEnum.getMessage());
  }

  /**
   * 异常构造错误
   *
   * @param logicException
   * @return
   */
  protected Result fail(LogicException logicException) {

    return fail(logicException.getCode(), logicException.getMessage());
  }


}
