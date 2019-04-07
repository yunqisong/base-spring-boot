package com.wosummer.demo.service;

import com.wosummer.demo.model.dto.template.PostTemplateForm;
import com.wosummer.demo.model.vo.template.TemplateSingleVo;

import java.util.List;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: ITemplateService
 */
@SuppressWarnings("ALL")
public interface ITemplateService {

  /**
   * 创建模板
   *
   * @param postTemplateForm
   * @return
   */
  TemplateSingleVo createTemplate(PostTemplateForm postTemplateForm);

  /**
   * 获取所有模板
   *
   * @return
   */
  List<TemplateSingleVo> getTemplate();

  /**
   * 删除模板
   *
   * @param templateId
   * @return
   */
  Boolean deleteTemplate(Integer templateId);
}
