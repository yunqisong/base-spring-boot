package com.wosummer.base.kit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * SpringKit created by yunqisong 2017/11/16
 * FOR : 全局获取Spring 上下文工具类
 * @author yunqisong
 */

@Component
@Order(Integer.MIN_VALUE)
@SuppressWarnings("ALL")
public class SpringKit implements ApplicationRunner {

  private static ApplicationContext applicationContext = null;

  @Autowired
  private ApplicationContext context;

  @Override
  public void run(ApplicationArguments args) throws Exception {

    // 设置上下文对象
    SpringKit.applicationContext = context;
  }

  public static void clearHolder() {

    applicationContext = null;
  }

  private static void assertContextInjected() {

    if (applicationContext == null) {
      throw new RuntimeException("Spring 上下文 获取失败!");
    }
  }

  public static ApplicationContext getApplicationContext() {
    assertContextInjected();
    return applicationContext;
  }

  public static <T> T getBean(String name) {

    assertContextInjected();
    return (T) applicationContext.getBean(name);
  }

  public static <T> T getBean(Class<T> requiredType) {

    assertContextInjected();
    return applicationContext.getBean(requiredType);
  }
}
