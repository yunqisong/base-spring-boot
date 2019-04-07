package com.wosummer.demo.serializer;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.wosummer.demo.kit.Kit;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * DeepStringToJsonSerializer for 递归彻底的String To Json
 *
 * @author yunqisong
 * @date 2018/11/7
 */
@SuppressWarnings("ALL")
public class DeepStringToJsonSerializer implements ObjectSerializer {


  @Override
  public void write(JSONSerializer serializer,
                    Object object,
                    Object fieldName,
                    Type fieldType,
                    int features) throws IOException {

    if (Objects.isNull(object)) {
      serializer.writeNull();
      return;
    }

    if (object instanceof String) {
      String str = (String) object;
      if (JSONUtil.isJson(str)) {
        JSON obj = Kit.deepStringToJSONObject(str);
        serializer.write(obj);
        return;
      }
    }

    serializer.write(object);
  }
}

