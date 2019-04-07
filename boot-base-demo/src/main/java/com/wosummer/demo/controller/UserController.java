package com.wosummer.demo.controller;

import com.wosummer.base.controller.BaseController;
import com.wosummer.base.model.Result;
import com.wosummer.demo.model.dto.user.UpdateUserForm;
import com.wosummer.demo.model.vo.user.UserListVo;
import com.wosummer.demo.model.vo.user.UserVo;
import com.wosummer.demo.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.wosummer.demo.enums.Const.HAS_ANY_ROLE_ADMIN;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: UserController
 */
@Slf4j
@SuppressWarnings("ALL")
@RestController
@RequestMapping("user")
@Api(tags = "用户相关 API")
public class UserController extends BaseController {

  @Autowired
  private IUserService userService;

  @GetMapping
  @PreAuthorize(HAS_ANY_ROLE_ADMIN)
  @ApiOperation("获取所有用户/ 单个用户")
  public Result<UserListVo> getAll(@RequestParam Integer id) {

    return success(userService.getAll(id));
  }

  @PostMapping
  @ApiOperation("更新用户信息")
  public Result<UserVo> updateUser(@Valid @RequestBody UpdateUserForm updateUserForm) {

    return success(userService.updateUser(updateUserForm));
  }

  @PostMapping("bindWechat")
  @PreAuthorize(HAS_ANY_ROLE_ADMIN)
  @ApiOperation("绑定微信")
  public Result bindWechat() {

    return success();
  }
}

