package com.wosummer.demo.model.dto.template;

import com.wosummer.demo.model.dto.mission.BaseMissionForm;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: PostTemplateForm
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
@ApiModel("提交模板表单")
@EqualsAndHashCode(callSuper = false)
public class PostTemplateForm extends BaseMissionForm {

  private Integer id;

}

