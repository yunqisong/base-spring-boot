package com.wosummer.base.aspect;

import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yunqisong
 * @date 2018/9/21
 * FOR: CoolJson自动输出
 */

@Slf4j
@Aspect
@Component
@SuppressWarnings("ALL")
public class CoolJsonAspect {

  /**
   * JSON转化为合理的输出
   *
   * @param json CoolJson转化的Json
   */
  private JSON coolJson(JSON json) {
    JSON retJson;
    if (json instanceof JSONObject) {
      JSONObject jsonObject = (JSONObject) json;
      JSONObject coolJson = new JSONObject();
      jsonObject.forEach((k, v) -> coolJson.put(CharUtil.isLetterUpper(k.charAt(0)) ? StrUtil.toCamelCase(k.toLowerCase()) : k, getValue(v)));
      retJson = coolJson;
    } else {
      retJson = ((JSONArray) json).stream().map(this::getValue).collect(Collectors.toCollection(JSONArray::new));
    }
    return retJson;
  }

  /**
   * Object 递归调用Json转化
   *
   * @param v 传输的值
   */
  private Object getValue(Object v) {

    return JSONUtil.isJson(v.toString()) ? coolJson((v instanceof JSON) ? (JSON) v : (JSON) JSON.parse(v.toString())) : v;
  }

  /**
   * JSON转化为合理的输出
   *
   * @param joinPoint 切点变量
   */
  @Around(value = "@annotation(com.wosummer.base.annotations.CoolJson)")
  public Object makeJsonCool(ProceedingJoinPoint joinPoint) throws Throwable {
    // 获取目标方法
    Object returnValue = joinPoint.proceed(joinPoint.getArgs());
    // 获取目标方法返回值
    return returnValue instanceof Page ?
      ((Page) returnValue).setRecords((List) ((Page) returnValue).getRecords().stream().map(this::getValue).collect(Collectors.toList())) :
      getValue(returnValue);
  }

}

