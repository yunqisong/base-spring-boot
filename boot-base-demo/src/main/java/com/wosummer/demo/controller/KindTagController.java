package com.wosummer.demo.controller;

import com.wosummer.base.controller.BaseController;
import com.wosummer.base.model.Result;
import com.wosummer.demo.model.dto.kindTag.PostKindTagForm;
import com.wosummer.demo.model.vo.kindTag.KindTagListVo;
import com.wosummer.demo.model.vo.kindTag.KindTagSingleVo;
import com.wosummer.demo.service.IKindTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: KindTagController
 */
@Slf4j
@SuppressWarnings("ALL")
@RestController
@RequestMapping("kind_tag")
@Api(tags = "标签相关 API")
public class KindTagController extends BaseController {

  @Autowired
  private IKindTagService kindTagService;

  @PostMapping
  @ApiOperation("保存标签")
  public Result<KindTagSingleVo> postKindTag(@Valid @RequestBody PostKindTagForm postKindTagForm) {

    return success(kindTagService.postKindTag(postKindTagForm));
  }

  @GetMapping
  @ApiOperation("获取所有标签")
  public Result<KindTagListVo> getAllKindTag() {

    return success(kindTagService.getAllKindTag());
  }
}

