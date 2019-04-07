package com.wosummer.base.aspect;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import com.wosummer.base.model.security.IJwtSecurityAble;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author yunqisong
 * @date 2018/10/4
 * FOR: 用户缓存池切面
 */

@Slf4j
@Aspect
@Component
@SuppressWarnings("ALL")
public class CleanUserPoolAspect {

  private static final TimedCache<String, IJwtSecurityAble> USER_POOL = CacheUtil.newTimedCache(1000L * 60L * 10L);


  public static void removeCache() {

    USER_POOL.clear();
  }

  public static IJwtSecurityAble setCache(IJwtSecurityAble iJwtSecurityAble) {

    USER_POOL.put(iJwtSecurityAble.getUsername(), iJwtSecurityAble);

    return iJwtSecurityAble;
  }

  public static IJwtSecurityAble setCache(String userName, IJwtSecurityAble iJwtSecurityAble) {

    USER_POOL.put(userName, iJwtSecurityAble);

    return iJwtSecurityAble;
  }

  public static IJwtSecurityAble getCache(String username) {

    return USER_POOL.get(username);
  }

  /**
   * 清除用户缓存池
   *
   * @param joinPoint 切点变量
   * @param retVal    返回值
   */
  @AfterReturning(pointcut = "@annotation(com.wosummer.base.annotations.CleanUserPool)", returning = "retVal")
  public void cleanUserPool(JoinPoint joinPoint, Object retVal) throws Throwable {
    // 获取目标方法
    removeCache();
  }


}

