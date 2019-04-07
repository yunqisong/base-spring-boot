package com.wosummer.demo.controller;

import com.wosummer.base.controller.BaseController;
import com.wosummer.base.model.Result;
import com.wosummer.demo.model.dto.template.PostTemplateForm;
import com.wosummer.demo.model.vo.template.TemplateSingleVo;
import com.wosummer.demo.service.ITemplateService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.wosummer.demo.enums.Const.HAS_ANY_ROLE_ADMIN;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: TemplateController
 */
@Slf4j
@SuppressWarnings("ALL")
@RequestMapping("template")
@RestController
@Api(tags = "模板相关 API")
public class TemplateController extends BaseController {

  @Autowired
  private ITemplateService templateService;


  @GetMapping
  public Result<List<TemplateSingleVo>> getTemplate() {

    return success(templateService.getTemplate());
  }

  @PostMapping
  @PreAuthorize(HAS_ANY_ROLE_ADMIN)
  public Result<TemplateSingleVo> createTemplate(@Valid @RequestBody PostTemplateForm postTemplateForm) {

    return success(templateService.createTemplate(postTemplateForm));
  }

  @Deprecated
  @PostMapping("{templateId}")
  @PreAuthorize(HAS_ANY_ROLE_ADMIN)
  public Result<TemplateSingleVo> updateTemplate(@PathVariable Integer templateId,
                                                 @Valid @RequestBody PostTemplateForm postTemplateForm) {

    postTemplateForm.setId(templateId);
    return success(templateService.createTemplate(postTemplateForm));
  }

  @DeleteMapping("{templateId}")
  @PreAuthorize(HAS_ANY_ROLE_ADMIN)
  public Result<Boolean> deleteTemplate(@PathVariable Integer templateId) {

    return success(templateService.deleteTemplate(templateId));
  }

}

