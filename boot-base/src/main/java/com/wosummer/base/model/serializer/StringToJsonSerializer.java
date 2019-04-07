package com.wosummer.base.model.serializer;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.lang.reflect.Type;

/**
 * @author yunqisong
 * @date 2018/4/21/021
 * FOR: String To JSON Serializer
 */
public class StringToJsonSerializer implements ObjectSerializer {

  @Override
  public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) {
    if (object == null) {
      serializer.getWriter().writeNull();
      return;
    }
    if (object instanceof String) {
      String str = (String) object;
      if (JSONUtil.isJson(str)) {
        String start = "[";
        if (str.startsWith(start)) {
          serializer.write(JSON.parseArray(str));
        } else {
          serializer.write(JSON.parseObject(str));
        }
      } else {
        serializer.write(str);
      }

      return;
    }
    serializer.write(object);
  }
}
