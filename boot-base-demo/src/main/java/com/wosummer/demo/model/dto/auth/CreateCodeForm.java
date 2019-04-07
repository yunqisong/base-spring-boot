package com.wosummer.demo.model.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: CreateCodeForm
 */
@Slf4j
@SuppressWarnings("ALL")
@ApiModel("获取邀请码表单")
@Data
@Accessors(chain = true)
public class CreateCodeForm {

  @ApiModelProperty("邀请码数量")
  private Integer count;

}

