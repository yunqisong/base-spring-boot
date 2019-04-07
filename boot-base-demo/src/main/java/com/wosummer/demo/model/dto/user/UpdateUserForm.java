package com.wosummer.demo.model.dto.user;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: UpdateUserForm
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
public class UpdateUserForm {

  @NotBlank(message = "密码不可以为空")
  @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "请填写不少于6个字符的密码，不允许特殊字符")
  private String password;

  @NotBlank(message = "密码不可以为空")
  @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "请填写不少于6个字符的密码，不允许特殊字符")
  private String newpassword;

  private UserInfo info;

  @NotNull(message = "ID不可以为空")
  private Integer id;

}

