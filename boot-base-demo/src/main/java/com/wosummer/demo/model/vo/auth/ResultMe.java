package com.wosummer.demo.model.vo.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: ResultMe
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
@ApiModel("用户登录返回信息")
public class ResultMe {

  @ApiModelProperty("ID")
  private Integer id;

  @ApiModelProperty("用户名")
  private String username;

  @ApiModelProperty("密码，讲道理这个不应该返回..")
  private String password;

  @ApiModelProperty("角色")
  private Integer role;

  @ApiModelProperty("附属信息")
  private LoginInfo info;

  @ApiModelProperty("Token凭证")
  private String token;

}

