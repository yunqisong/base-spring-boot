package com.wosummer.demo.controller;

import com.wosummer.base.controller.BaseController;
import com.wosummer.base.kit.SecurityKit;
import com.wosummer.base.model.Result;
import com.wosummer.demo.model.dto.auth.CreateCodeForm;
import com.wosummer.demo.model.dto.auth.LoginForm;
import com.wosummer.demo.model.dto.auth.RegisterForm;
import com.wosummer.demo.model.vo.auth.InvitecodeVo;
import com.wosummer.demo.model.vo.auth.ResultMe;
import com.wosummer.demo.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.wosummer.demo.enums.Const.*;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: AuthController
 */
@Slf4j
@SuppressWarnings("ALL")
@RestController
@RequestMapping("auth")
@Api(tags = "权限相关 API")
public class AuthController extends BaseController {

  @Autowired
  private IUserService userService;

  @PostMapping("login")
  @ApiOperation("登录")
  public Result<ResultMe> login(@Valid @RequestBody LoginForm loginForm) {

    return success(userService.login(loginForm));
  }

  @GetMapping("logout")
  @ApiOperation("登出")
  public Result logout() {

    return success();
  }

  @GetMapping("status")
  @ApiOperation("获取登录状态信息")
  public Result<ResultMe> status() {

    return success(userService.me(SecurityKit.currentUser()));
  }

  @PostMapping("register")
  @ApiOperation("注册")
  public Result<ResultMe> register(@Valid @RequestBody RegisterForm registerForm) {

    return success(userService.register(registerForm));
  }

  @GetMapping("code")
  @PreAuthorize(HAS_ANY_ROLE_ADMIN)
  @ApiOperation("获取所有邀请码")
  public Result<InvitecodeVo> getAllCode() {

    return success(userService.getAllCode());
  }

  @PostMapping("code")
  @PreAuthorize(HAS_ANY_ROLE_ADMIN)
  @ApiOperation("新建邀请码")
  public Result<InvitecodeVo> createCode(@RequestBody CreateCodeForm createCodeForm) {

    return success(userService.createCode(createCodeForm.getCount()));
  }


}

