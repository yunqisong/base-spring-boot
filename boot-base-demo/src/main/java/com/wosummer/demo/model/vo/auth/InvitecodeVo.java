package com.wosummer.demo.model.vo.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: InvitecodeVo
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
@ApiModel("邀请码返回")
public class InvitecodeVo {

  @ApiModelProperty("邀请码数组")
  private String[] codes;

}

