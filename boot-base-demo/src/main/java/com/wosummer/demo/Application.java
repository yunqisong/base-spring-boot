package com.wosummer.demo;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.spring4all.swagger.EnableSwagger2Doc;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author yunqisong
 * @for: Application for
 * @date 2018/11/2
 */
@Slf4j
@SuppressWarnings("ALL")
@EnableSwagger2Doc
@EnableSwaggerBootstrapUI
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = "com.wosummer")
@MapperScan("com.wosummer.*.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class Application {

  public static final String SPRING_START_SUCCEFULY = "Spring Start Succefuly !";

  public static void main(String... args) {

    log.info(SPRING_START_SUCCEFULY);
    SpringApplication.run(Application.class, args);

  }


}



