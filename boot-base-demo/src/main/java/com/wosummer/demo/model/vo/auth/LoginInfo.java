package com.wosummer.demo.model.vo.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: LoginInfo
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
@ApiModel("info 具体附属信息")
public class LoginInfo {

  @ApiModelProperty("QQ")
  private String qq;

  @ApiModelProperty("微信OPENID")
  private String openid;

  @ApiModelProperty("创建时间")
  private Long borntime;

  @ApiModelProperty("昵称")
  private String nickname;

  @ApiModelProperty("权限")
  private List<String> permissions;

}

