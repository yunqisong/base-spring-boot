package com.wosummer.base.config;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.wosummer.base.interceptor.ApiLogInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

/**
 * @author yunqisong
 * @date 2018/9/28
 * FOR: WebMvc 整体配置
 */

@Slf4j
@SuppressWarnings("ALL")
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Value("${spring.http.converters.preferred-json-mapper}")
  private String[] jsonMappers;

  private static final String FAST_JSON = "fastjson";

  /**
   * 添加FastJson转化器
   *
   * @param converters
   */
  private void addFastJsonConverter(List<HttpMessageConverter<?>> converters) {
    converters.removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);
    converters.removeIf(converter -> converter instanceof GsonHttpMessageConverter);
    log.info("Add a FastJsonHttpMessageConverter as HttpMessageConverter");
    //定义一个转换消息的对象
    FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
    //添加fastjson的配置信息 比如 ：是否要格式化返回的json数据
    FastJsonConfig fastJsonConfig = new FastJsonConfig();
    fastJsonConfig.setSerializerFeatures(
      /* 拒绝循环引用*/
      SerializerFeature.DisableCircularReferenceDetect,
      /* 空对象null输出*/
      SerializerFeature.WriteMapNullValue,
      /* 空数组转数组*/
      SerializerFeature.WriteNullListAsEmpty,
      /* 空boolean 转 false*/
      SerializerFeature.WriteNullBooleanAsFalse,
      /* null字符串 转化为""字符串*/
      SerializerFeature.WriteNullStringAsEmpty,
      /* 枚举转 调用 name() 而不是toString*/
      SerializerFeature.WriteEnumUsingName,
      /* 允许字段排序 */
      SerializerFeature.SortField
    );
    /* 解决Long转json精度丢失的问题 */
//    SerializeConfig serializeConfig = SerializeConfig.globalInstance;
//    serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
//    serializeConfig.put(Long.class, ToStringSerializer.instance);
//    serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
//    fastJsonConfig.setSerializeConfig(serializeConfig);

    //在转换器中添加配置信息
    fastConverter.setFastJsonConfig(fastJsonConfig);
    //将转换器添加到converters中
    converters.add(fastConverter);
  }

  /**
   * 配置Json转化器
   *
   * @param converters
   */
  @Override
  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    if (Arrays.stream(jsonMappers).anyMatch(e -> e.equalsIgnoreCase(FAST_JSON))) {
      this.addFastJsonConverter(converters);
    }
    log.debug("There has been {} HttpMessageConverters when extend", converters.size());

    converters.forEach(converter -> log.debug("HttpMessageConverter: {}", converter.getClass().getName()));
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new ApiLogInterceptor()).addPathPatterns("/**");
  }

  /**
   * mybatis-plus分页插件<br>
   * 文档：http://mp.baomidou.com<br>
   */
  @Bean
  public PaginationInterceptor paginationInterceptor() {
    return new PaginationInterceptor();
  }

}

