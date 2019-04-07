package com.wosummer.demo.model.dto.auth;

import com.wosummer.demo.annotations.UsernameExist;
import com.wosummer.demo.model.dto.user.UserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: RegisterForm
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class RegisterForm extends UserInfo {

  @NotBlank(message = "用户名不可以为空")
  @Pattern(regexp = "^[a-zA-Z0-9]{5,24}$", message = "请输入正确账号，英文或数字 5-24字符")
  @UsernameExist
  private String username;

  @NotBlank(message = "邀请码不可以为空")
  @Pattern(regexp = "^[0-9a-zA-Z]{16}$", message = "请填写16位数字或字母邀请码")
  private String inviteCode;

}

