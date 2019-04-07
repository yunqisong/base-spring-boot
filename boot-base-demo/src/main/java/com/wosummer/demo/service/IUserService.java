package com.wosummer.demo.service;

import com.wosummer.demo.model.dto.auth.LoginForm;
import com.wosummer.demo.model.dto.auth.RegisterForm;
import com.wosummer.demo.model.dto.user.UpdateUserForm;
import com.wosummer.demo.model.jwt.JwtUser;
import com.wosummer.demo.model.vo.auth.InvitecodeVo;
import com.wosummer.demo.model.vo.auth.ResultMe;
import com.wosummer.demo.model.vo.user.UserListVo;
import com.wosummer.demo.model.vo.user.UserVo;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: IUserService
 */
@SuppressWarnings("ALL")
public interface IUserService {

  /**
   * 登录接口
   *
   * @param loginForm 登录表单
   * @return 返回用户信息
   */
  ResultMe login(LoginForm loginForm);

  /**
   * 获取当前登录状态
   *
   * @return
   */
  ResultMe me(JwtUser jwtUser);

  /**
   * 创建邀请码
   *
   * @param count
   * @return
   */
  InvitecodeVo createCode(Integer count);

  /**
   * 获取所有可用邀请码
   *
   * @return
   */
  InvitecodeVo getAllCode();

  /**
   * 注册
   *
   * @param registerForm
   * @return
   */
  ResultMe register(RegisterForm registerForm);

  /**
   * 获取所有用户列表
   *
   * @param id
   * @return
   */
  UserListVo getAll(Integer id);

  /**
   * 用户个体
   *
   * @param updateUserForm
   * @return
   */
  UserVo updateUser(UpdateUserForm updateUserForm);
}

