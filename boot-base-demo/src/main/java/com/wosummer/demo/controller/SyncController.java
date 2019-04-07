package com.wosummer.demo.controller;

import com.wosummer.base.controller.BaseController;
import com.wosummer.base.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: SyncController
 */
@Slf4j
@RequestMapping("sync")
@RestController
@Api(tags = "同步相关 API")
public class SyncController extends BaseController {

  @PostMapping("bjh")
  @ApiOperation("提交到百家号")
  public Result postBjh() {

    return success();
  }

  @GetMapping("account")
  @ApiOperation("获取账号")
  public Result getAccount() {

    return success();
  }

  @GetMapping("cookies")
  @ApiOperation("获取CookiesById")
  public Result getCookiesById() {

    return success();
  }

  @PostMapping("toutiao")
  @ApiOperation("提交到头条")
  public Result postToutiao() {

    return success();
  }
}

