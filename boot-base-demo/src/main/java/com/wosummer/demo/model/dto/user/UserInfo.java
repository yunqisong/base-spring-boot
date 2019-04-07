package com.wosummer.demo.model.dto.user;

import com.wosummer.demo.annotations.UsernameExist;
import com.wosummer.demo.model.dto.auth.RegisterForm;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: UserInfo
 */
@Slf4j
@SuppressWarnings("ALL")
@Data
@Accessors(chain = true)
public class UserInfo {

  @NotBlank(message = "用户名不可以为空")
  @Pattern(regexp = "^[a-zA-Z0-9]{5,24}$", message = "请输入正确账号，英文或数字 5-24字符")
  private String username;

  @NotBlank(message = "密码不可以为空")
  @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "请填写不少于6个字符的密码，不允许特殊字符")
  private String password;

  @NotBlank(message = "QQ号不可以为空")
  @Pattern(regexp = "^[0-9]{6,11}$", message = "请填写正确的QQ号")
  private String qq;

  @NotBlank(message = "昵称不可以为空")
  @Pattern(regexp = "^[\\u4E00-\\u9FA5]{2}$", message = "请填写两个字之内的中文昵称")
  private String nickname;

}

