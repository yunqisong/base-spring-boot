package com.wosummer.demo.model.dto.auth;

import com.wosummer.demo.annotations.UsernameExist;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: LoginForm
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
@ApiModel("登录表单")
public class LoginForm {

  @ApiModelProperty("用户名")
  @NotBlank(message = "用户名不可以为空!")
  @Pattern(regexp = "^[a-zA-Z0-9]{5,24}$", message = "请输入正确账号，英文或数字 5-24字符")
  private String username;

  @ApiModelProperty("密码")
  @NotBlank(message = "密码不可以为空!")
  @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "密码 (不少于6个字符，不允许特殊字符)")
  private String password;

}

