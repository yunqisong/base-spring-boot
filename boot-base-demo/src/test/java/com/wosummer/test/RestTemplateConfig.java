package com.wosummer.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author yunqisong
 * @date 2019-04-07
 * @for: RestTemplateConfig
 */
@Slf4j
@Configuration
public class RestTemplateConfig {

  @Bean
  public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
    RestTemplate restTemplate = new RestTemplate(factory);
    restTemplate.getInterceptors().add(clientHttpRequestInterceptor());
    return restTemplate;
  }

  @Bean
  public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
    SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    factory.setReadTimeout(5000);
    factory.setConnectTimeout(5000);
    return factory;
  }

  @Bean
  public ClientHttpRequestInterceptor clientHttpRequestInterceptor() {
    return (request, body, execution) -> {
      log.info("请求地址：{}", request.getURI());
      log.info("请求方法： {}", request.getMethod());
      log.info("请求内容：{}", new String(body));
      log.info("请求头：{}", request.getHeaders());
      return execution.execute(request, body);
    };
  }

}
