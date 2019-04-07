package com.wosummer.base.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yunqisong
 * @for: Http401AuthenticationEntryPoint for 401 过滤
 * @date 2018/10/24
 */
@Slf4j
@SuppressWarnings("ALL")
public class Http401AuthenticationEntryPoint implements AuthenticationEntryPoint {


  @Override
  public void commence(HttpServletRequest request,
                       HttpServletResponse response,
                       AuthenticationException authException) throws IOException, ServletException {

    response.setHeader("X-Authenticate", "");
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
  }
}

