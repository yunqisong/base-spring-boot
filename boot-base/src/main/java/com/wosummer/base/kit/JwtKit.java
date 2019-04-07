package com.wosummer.base.kit;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.wosummer.base.model.security.IJwtSecurityAble;
import com.wosummer.base.model.security.LoginInfoHolder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * JKhaled created by yunqisong@foxmail.com 2017/11/21
 * FOR : JWT Token构造工具
 *
 * @author yunqisong
 */
@SuppressWarnings("ALL")
@Slf4j
public class JwtKit {
  /**
   * 构造函数私有化
   */
  private static final Map<String, Claims> TOKEN_STORE = new HashMap<>();

  private static final String CLAIM_KEY_USERNAME = "sub";

  private static final String CLAIM_KEY_CREATED = "created";

  private static final String LOGIN_TYPE = "loginType";

  private static final String USER_DETAILS_SERVICE_IMPL = "userDetailsServiceImpl";

  /**
   * 加密令牌的私钥，默认值由配置文件给出
   */
  private static String secret;
  /**
   * 令牌Token的有效时长，单位秒，默认值由配置文件给出
   */

  private static Long expiration;
  /**
   * Token前缀,由系统配置文件给出
   */

  private static String tokenPre;

  private static String header;

  private static String cookieName;

  private static String parameterName;

  private static String tokenPrefix;


  public static void init(String secret,
                          Long expiration,
                          String tokenPre,
                          String header,
                          String cookieName,
                          String parameterName,
                          String tokenPrefix) {
    JwtKit.secret = secret;
    JwtKit.expiration = expiration;
    JwtKit.tokenPre = tokenPre;
    JwtKit.header = header;
    JwtKit.cookieName = cookieName;
    JwtKit.parameterName = parameterName;
    JwtKit.tokenPrefix = tokenPrefix;
  }

  /**
   * 对外暴露的核心方法，获取token中的account信息
   *
   * @param authToken
   * @return
   */
  public static String getUsernameFormToken(String authToken) {

    String account;

    try {
      Claims claims = getClaimsFromToken(authToken);
      account = claims.getSubject();
    } catch (Exception e) {
      account = null;
    }
    return account;
  }

  /**
   * 对外暴露的核心方法，获取token中的account信息
   *
   * @param authToken
   * @return
   */
  public static String getLoginTypeFormToken(String authToken) {

    String loginType;

    try {
      Claims claims = getClaimsFromToken(authToken);
      loginType = (String) claims.get(LOGIN_TYPE);
    } catch (Exception e) {
      loginType = null;
    }
    return loginType;
  }

  /**
   * 将token信息解析出来对应的 Claims
   *
   * @param token
   * @return
   */
  private static Claims getClaimsFromToken(String token) {

    Claims claims;
    try {
      claims = TOKEN_STORE.get(token);
      if (claims == null) {
        Claims temp = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        TOKEN_STORE.put(token, temp);
        claims = temp;
      }
    } catch (Exception e) {
      claims = null;
    }
    return claims;
  }

  /**
   * 获取Token的过期日期
   *
   * @param token
   * @return
   */
  public static Date getExpirationDateFromToken(String token) {

    Date expiration;
    try {
      Claims claims = getClaimsFromToken(token);
      expiration = claims.getExpiration();
    } catch (Exception e) {
      expiration = null;
    }
    return expiration;
  }

  public static Map<String, String> getExtInfoFormToken(String token) {

    Dict expiration = Dict.create();
    try {
      Claims claims = getClaimsFromToken(token);
      for (String k : claims.keySet()) {
        if (!k.equals(CLAIM_KEY_USERNAME) && !k.equals(LOGIN_TYPE) && !k.equals(CLAIM_KEY_CREATED) && claims.get(k) != null) {
          log.info("变量 :{}  值:{}", k, String.valueOf(claims.get(k)));
          expiration.put(k, String.valueOf(claims.get(k)));
        }
      }
    } catch (Exception e) {
      log.info("Token转化错误!\n {}", e);
      expiration = Dict.create();
    }
    return (Map) expiration;
  }

  /**
   * 获取Token的创建时间
   *
   * @param token
   * @return
   */
  private static Date getCreatedFormToken(String token) {
    Date created;
    try {
      Claims claims = getClaimsFromToken(token);
      created = DateUtil.date((Long) claims.get(CLAIM_KEY_CREATED));
    } catch (Exception e) {
      created = null;
    }
    return created;
  }

  /**
   * 判断Token是否过期
   *
   * @param token
   * @return
   */
  private static Boolean isTokenExpired(String token) {
    Date expiration = getExpirationDateFromToken(token);
    return expiration.before(new Date());
  }

  /**
   * 验证一个Token是否合法
   *
   * @param token
   * @param iJwtAndSecurityAble
   * @return
   */
  public static Boolean validateToken(String token, IJwtSecurityAble iJwtAndSecurityAble) {
    Date created = getCreatedFormToken(token);

    return !isTokenExpired(token) &&
      (iJwtAndSecurityAble.getLastModifyPasswordTime() != null && iJwtAndSecurityAble.getLastModifyPasswordTime().before(created));
  }


  /**
   * 根据用户信息生成token
   *
   * @param iJwtAndSecurityAble 根据详情创建Token
   * @return 根据详情创建Token
   */
  public static String generateToken(IJwtSecurityAble iJwtSecurityAble) {

    return tokenPre + Jwts.builder()
      .setClaims(
        Dict.create()
          .set(CLAIM_KEY_USERNAME, iJwtSecurityAble.getUsername())
          .set(CLAIM_KEY_CREATED, DateUtil.date())
          .set(LOGIN_TYPE, iJwtSecurityAble.getLoginType())
        // 填充额外信息
      )
      .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
      .signWith(SignatureAlgorithm.HS512, secret)
      .compact();
  }

  public static void validateToken(HttpServletRequest request) {

    // 解析token
    String tokenAllStr = request.getHeader(header);

    if (StrUtil.isBlank(tokenAllStr) && ArrayUtil.isNotEmpty(request.getCookies())) {
      tokenAllStr = Arrays
        .stream(request.getCookies())
        .filter(c -> cookieName.equals(c.getName()))
        .map(Cookie::getValue)
        .findFirst()
        .orElse("");
    }

    if (StrUtil.isBlank(tokenAllStr)) {
      tokenAllStr = request.getParameter(parameterName);
    }
    tokenAllStr = URLUtil.decode(tokenAllStr);

    if (StrUtil.isNotBlank(tokenAllStr) && tokenAllStr.startsWith(tokenPrefix)) {
      // 符合要求的token
      String token = tokenAllStr.substring(tokenPrefix.length());
      String account = JwtKit.getUsernameFormToken(token);
      String loginType = JwtKit.getLoginTypeFormToken(token);
      LoginInfoHolder.setLoginType(loginType);
      LoginInfoHolder.setExtInfo(JwtKit.getExtInfoFormToken(token));
      if (StrUtil.isNotBlank(account)) {
        // 从token中认证用户名
        IJwtSecurityAble userDetails = (IJwtSecurityAble) ((UserDetailsService) SpringKit.getBean(USER_DETAILS_SERVICE_IMPL)).loadUserByUsername(account);

        if (JwtKit.validateToken(token, userDetails)) {
          // 认证成功 构造Spring认证令牌
          UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

          authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          // 存入令牌
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
          log.info("用户: {} ,权限 : {} 以登录方式:{} 认证成功。", account, authenticationToken.getAuthorities(), loginType);
        }

      }

    }

  }


}
