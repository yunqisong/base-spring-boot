package com.wosummer.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wosummer.base.kit.JwtKit;
import com.wosummer.base.model.enums.BaseResultEnum;
import com.wosummer.base.model.exceptions.LogicException;
import com.wosummer.base.service.CommonService;
import com.wosummer.demo.entity.Invitecode;
import com.wosummer.demo.entity.User;
import com.wosummer.demo.enums.EnableEnum;
import com.wosummer.demo.enums.RoleEnum;
import com.wosummer.demo.mapper.InvitecodeMapper;
import com.wosummer.demo.mapper.UserMapper;
import com.wosummer.demo.model.dto.auth.LoginForm;
import com.wosummer.demo.model.dto.auth.RegisterForm;
import com.wosummer.demo.model.dto.user.UpdateUserForm;
import com.wosummer.demo.model.jwt.JwtUser;
import com.wosummer.demo.model.vo.auth.InvitecodeVo;
import com.wosummer.demo.model.vo.auth.LoginInfo;
import com.wosummer.demo.model.vo.auth.ResultMe;
import com.wosummer.demo.model.vo.user.UserListVo;
import com.wosummer.demo.model.vo.user.UserVo;
import com.wosummer.demo.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author yunqisong
 * @date 2019-04-05
 * @for: UserServiceImpl
 */
@Slf4j
@SuppressWarnings("ALL")
@Service
public class UserServiceImpl implements IUserService {


  @Autowired
  private UserMapper userMapper;

  @Autowired
  private CommonService commonService;

  @Autowired
  private InvitecodeMapper invitecodeMapper;

  @Autowired
  private AuthenticationManager authenticationManager;

  /**
   * 登录公共验证接口
   *
   * @param loginForm
   * @return
   */
  private JwtUser validate(LoginForm loginForm) {

    try {
      UsernamePasswordAuthenticationToken upToken =
        new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword());

      Authentication authentication = authenticationManager.authenticate(upToken);

      log.debug("Login successfully, generate token for user: {} after authentication", loginForm);

      return (JwtUser) authentication.getPrincipal();
    } catch (Exception e) {
      log.debug("Login Error : {}", e);
      throw new LogicException(BaseResultEnum.USERNAME_OR_PASSWORD_NOT_CORRECT);
    }
  }

  /**
   * 组装Me信息
   *
   * @param jwtUser
   * @return
   */
  @Override
  public ResultMe me(JwtUser jwtUser) {
    ResultMe resultMe = new ResultMe();
    BeanUtil.copyProperties(jwtUser.getUser(), resultMe);
    if (StrUtil.isNotBlank(jwtUser.getUser().getInfo())) {
      resultMe.setInfo(JSON.parseObject(jwtUser.getUser().getInfo(), LoginInfo.class));
    }
    return resultMe;
  }

  /**
   * 创建邀请码
   *
   * @param count
   * @return
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public InvitecodeVo createCode(Integer count) {
    Integer createCount = Objects.isNull(count) ? 1 : count;

    String[] codes = new String[createCount];
    for (int i = 0; i < createCount; i++) {
      // 加密串，并16进制
      String now = RandomUtil.randomNumbers(10) + DateTime.now().toString();
      String code = HexUtil.encodeHexStr(SecureUtil.md5(now)).substring(8, 24);

      // 保存邀请码
      Invitecode invitecode = new Invitecode().setCode(code).setEnable(EnableEnum.YES.getCode());
      commonService.insertOrUpdate(invitecode, invitecodeMapper);

      // 放入到返回值
      codes[i] = code;
    }

    return new InvitecodeVo().setCodes(codes);
  }

  /**
   * 获取所有可用邀请码
   *
   * @return
   */
  @Override
  public InvitecodeVo getAllCode() {

    return new InvitecodeVo()
      .setCodes(
        invitecodeMapper
          .selectList(new QueryWrapper<Invitecode>().eq(Invitecode.ENABLE, EnableEnum.YES.getCode()))
          .stream()
          .map(Invitecode::getCode)
          .toArray(String[]::new)
      );
  }

  /**
   * 注册
   *
   * @param registerForm
   * @return
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public ResultMe register(RegisterForm registerForm) {
    User user = new User();
    BeanUtil.copyProperties(registerForm, user);

    LoginInfo loginInfo
      = new LoginInfo()
      .setBorntime(DateTime.now().getTime())
      .setNickname(registerForm.getNickname())
      .setPermissions(Collections.EMPTY_LIST)
      .setQq(registerForm.getQq());
    user.setInfo(JSON.toJSONString(loginInfo)).setRole(RoleEnum.USER.getCode());

    commonService.insertOrUpdate(user, userMapper);

    return login(new LoginForm().setUsername(registerForm.getUsername()).setPassword(registerForm.getPassword()));
  }

  /**
   * 获取所有用户列表
   *
   * @return
   */
  @Override
  public UserListVo getAll(Integer id) {

    if (Objects.nonNull(id)) {
      return new UserListVo().setUser(new UserVo(userMapper.selectById(id)));
    }

    return new UserListVo().setUsers(userMapper.selectList(null).stream().map(UserVo::new).collect(Collectors.toList()));
  }

  /**
   * 更新用户
   *
   * @param updateUserForm
   * @return
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public UserVo updateUser(UpdateUserForm updateUserForm) {
    User user = userMapper.selectById(updateUserForm.getId());

    if (!Objects.equals(user.getPassword(), updateUserForm.getPassword())) {
      throw new LogicException(BaseResultEnum.INVALID_PARAMETERS, "密码不正确");
    }

    BeanUtil.copyProperties(updateUserForm, user);

    user.setPassword(StrUtil.isNotBlank(updateUserForm.getNewpassword()) ? updateUserForm.getNewpassword() : updateUserForm.getPassword());

    commonService.insertOrUpdate(user, userMapper);

    return new UserVo(user);
  }

  /**
   * 用户登录
   *
   * @param loginForm 登录表单
   * @return
   */
  @Override
  public ResultMe login(LoginForm loginForm) {
    JwtUser jwtUser = validate(loginForm);
    return me(jwtUser).setToken(JwtKit.generateToken(jwtUser));
  }


}

