package com.wosummer.test;

import com.wosummer.demo.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yunqisong
 * @date 2019-04-07
 * @for: AuthTest
 */
@Slf4j
@Transactional  // 事务
@Rollback       // 回滚
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AuthTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void login() {
//    Result<ResultMe> result = restTemplate.postForObject("/auth/login", new LoginForm().setUsername("admin").setPassword("johnson"), Result.class);
//    assertThat(body).isEqualTo("Hello World");
//    ParameterizedTypeReference parameterizedTypeReference = new ParameterizedTypeReference<Result<ResultMe>>() {};

//    XXX为实例化的类型
//    HttpHeaders headers = new HttpHeaders();
//    headers.setContentType(MediaType.APPLICATION_JSON);
//    HttpEntity<String> entity = new HttpEntity<>(JSONUtil.toJsonPrettyStr(""), headers);
//    ResponseEntity<Result<ResultMe>> result = restTemplate.postForEntity("/auth/login", new LoginForm().setUsername("admin").setPassword("johnson"), Result.class);
//    log.debug("body:\n{}", JSONUtil.toJsonPrettyStr(result.getData().getId()));
  }

}

