package com.wosummer.base.model.exceptions;

import com.wosummer.base.model.enums.BaseResultEnum;
import com.wosummer.base.model.enums.IResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yunqisong
 * @date 2018/10/3
 * FOR: 逻辑处理异常
 */

@Slf4j
@SuppressWarnings("ALL")
@Data
@EqualsAndHashCode(callSuper = false)
public class LogicException extends RuntimeException {

  private Integer code;

  private String message;

  public LogicException(Integer code, String message) {
    super(message);
    this.code = code;
    this.message = message;
  }

  public LogicException(String message) {

    this(BaseResultEnum.INVALID_PARAMETERS.getCode(), message);
  }

  public LogicException(IResultEnum resultEnum) {

    this(resultEnum.getCode(), resultEnum.getMessage());
  }

  public LogicException(IResultEnum resultEnum, String msg) {

    this(resultEnum.getCode(), resultEnum.getMessage() + ":" + msg);
  }
}
