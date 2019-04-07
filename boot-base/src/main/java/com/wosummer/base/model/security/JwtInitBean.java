package com.wosummer.base.model.security;

import com.wosummer.base.kit.JwtKit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * JKhaled created by yunqisong@foxmail.com 2017/11/21
 * FOR : JWT 初始化类
 *
 * @author yunqisong
 */
@Component
public class JwtInitBean implements ApplicationRunner {
  /**
   * 加密令牌的私钥，默认值由配置文件给出
   */
  @Value("${jwt.secret}")
  private String secret;
  /**
   * 令牌Token的有效时长，单位秒，默认值由配置文件给出
   */
  @Value("${jwt.expiration}")
  private Long expiration;
  /**
   * Token前缀,由系统配置文件给出
   */
  @Value("${jwt.tokenHead}")
  private String tokenHead;

  @Value("${jwt.header}")
  private String header;

  @Value("${jwt.cookieName}")
  private String cookieName;

  @Value("${jwt.parameterName}")
  private String parameterName;

  @Value("${jwt.tokenHead}")
  private String tokenPrefix;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    JwtKit.init(secret, expiration, tokenHead, header, cookieName, parameterName, tokenPrefix);
  }
}
