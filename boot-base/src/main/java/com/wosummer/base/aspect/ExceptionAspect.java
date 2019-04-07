package com.wosummer.base.aspect;

import com.wosummer.base.model.Result;
import com.wosummer.base.model.enums.BaseResultEnum;
import com.wosummer.base.model.exceptions.LogicException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.NestedServletException;

/**
 * @author yunqisong
 * @date 2018/10/3
 * FOR: 错误切面拦截
 */

@Slf4j
@SuppressWarnings("ALL")
@Order(Integer.MIN_VALUE)
@ControllerAdvice
public class ExceptionAspect {


  @ExceptionHandler(AssertionError.class)
  @ResponseBody
  public Result handleAssertionError(AssertionError e) {
    log.info("AssertionError occur: {}", e);
    return Result.fail(BaseResultEnum.DATA_ERROR.getCode(), BaseResultEnum.DATA_ERROR.getMessage() + ":" + e.getMessage());
  }

  @ExceptionHandler(NestedServletException.class)
  @ResponseBody
  public Result handleNestedServletException(NestedServletException e) {
    log.info("NestedServletException occur: {}", e);
    return Result.fail(BaseResultEnum.DATA_ERROR.getCode(), BaseResultEnum.DATA_ERROR.getMessage() + ":" + e.getMessage().split(":")[1]);
  }

  @ExceptionHandler(LogicException.class)
  @ResponseBody
  public Result handleLogicException(LogicException e) {
    log.warn("Logic Exception occur: {}", e.getMessage());
    return Result.fail(e);
  }

  @ExceptionHandler(LockedException.class)
  @ResponseBody
  public Result handleLockedException(LockedException e) {
    log.warn("Logic Exception occur: {}", e.getMessage());
    return Result.fail("账号已被禁用!");
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseBody
  public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    log.warn("Logic Exception occur: {}", e.getMessage());
    return Result.fail(e.getMessage().contains("POST") ? BaseResultEnum.METHOD_ERROR_POST : BaseResultEnum.METHOD_ERROR_GET);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseBody
  public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
    log.warn("Logic Exception occur: {}", e.getMessage());
    return Result.fail(BaseResultEnum.JSON_SWITCH_ERROR.getCode(), e.getMessage());
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  @ResponseBody
  public Result handleUsernameNotFoundException(UsernameNotFoundException e) {
    log.warn("Logic Exception occur: {}", e.getMessage());
    return Result.fail(BaseResultEnum.USER_NOT_EXIST.getCode(), BaseResultEnum.USER_NOT_EXIST.getMessage());
  }

  @ExceptionHandler(BadCredentialsException.class)
  @ResponseBody
  public Result handleBadCredentialsException(BadCredentialsException e) {
    log.warn("BadCredentialsException occur: {}", e.getMessage());
    return Result.fail(BaseResultEnum.USERNAME_OR_PASSWORD_NOT_CORRECT.getCode(), BaseResultEnum.USERNAME_OR_PASSWORD_NOT_CORRECT.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    log.warn("MethodArgumentNotValidException occur: {}", e.getMessage());
    return Result.fail(BaseResultEnum.INVALID_PARAMETERS.getCode(), e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
  }

  @ExceptionHandler(AccessDeniedException.class)
  @ResponseBody
  public Result handleAccessDeniedException(AccessDeniedException e) throws AccessDeniedException {

    log.warn("权限验证异常 ！\n {}", e);
    throw e;
  }


  @ExceptionHandler(Exception.class)
  @ResponseBody
  public Result handleException(Exception e) {

    log.error("Uncertain exception occur: {}", e.getMessage(), e);
    return Result.fail(BaseResultEnum.BAD_REQUEST.getCode(), BaseResultEnum.BAD_REQUEST.getMessage());
  }

}

