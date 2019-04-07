package com.wosummer.demo.controller;

import com.wosummer.base.controller.BaseController;
import com.wosummer.base.model.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: UploadRepeatRateCheckController
 */
@Slf4j
@RequestMapping("uploadRepeatRateCheck")
@RestController
@Api(tags = "查重相关 API")
public class UploadRepeatRateCheckController extends BaseController {

  @PostMapping
  public Result uploadRepeatRateCheckPost() {

    return success();
  }

  @GetMapping
  public Result uploadRepeatRateCheckGet() {

    return success();
  }

}

