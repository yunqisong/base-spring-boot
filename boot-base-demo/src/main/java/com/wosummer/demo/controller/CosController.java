package com.wosummer.demo.controller;

import com.wosummer.base.controller.BaseController;
import com.wosummer.base.model.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: CosController
 */
@Slf4j
@SuppressWarnings("ALL")
@RestController
@RequestMapping("cos")
@Api(tags = "COS相关 API")
public class CosController extends BaseController {

  @GetMapping
  public Result getTencentAuthorization() {

    return success();
  }
}

