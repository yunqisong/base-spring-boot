package com.wosummer.demo.model.dto.mission;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: PostMissionForm
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
@ApiModel("提交任务")
@EqualsAndHashCode(callSuper = false)
public class PostMissionForm extends BaseMissionForm {

  private String id;

  @ApiModelProperty("变为的状态")
  private String state;

}

