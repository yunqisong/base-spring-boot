package com.wosummer.base.filter;

import com.wosummer.base.kit.JwtKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JKhaled created by yunqisong@foxmail.com 2017/11/21
 * FOR : JWT Token全局过滤器
 *
 * @author yunqisong
 */
@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    if (SecurityContextHolder.getContext().getAuthentication() == null) {
      JwtKit.validateToken(request);
    }
    filterChain.doFilter(request, response);
  }


}
