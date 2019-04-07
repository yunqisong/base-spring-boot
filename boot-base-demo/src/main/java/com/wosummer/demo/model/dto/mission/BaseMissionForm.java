package com.wosummer.demo.model.dto.mission;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: BaseMissionForm
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
@ApiModel("基础Mision")
public class BaseMissionForm {

  private MissionInfo info;

  private String type;

  private Integer count = 1;

  private String content;

  private Long deadline;

  /**
   * 以 example_images 字段接收
   */
  @JSONField(name = "example_images")
  @ApiModelProperty(name = "example_images")
  private List<String> exampleImages;
}

