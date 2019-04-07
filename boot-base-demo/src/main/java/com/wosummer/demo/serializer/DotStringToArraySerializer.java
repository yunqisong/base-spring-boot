package com.wosummer.demo.serializer;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.wosummer.demo.enums.Const;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.Objects;

/**
 * @author yunqisong
 * @date 2019-04-06
 * @for: DotStringToArraySerializer
 */
@Slf4j
@SuppressWarnings("ALL")
public class DotStringToArraySerializer implements ObjectSerializer {

  @Override
  public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {

    if (Objects.isNull(object)) {
      serializer.write(Collections.EMPTY_LIST);
      return;
    }

    if (object instanceof String) {
      String str = (String) object;
      if (StrUtil.isBlank(str)) {
        serializer.write(Collections.EMPTY_LIST);
        return;
      }
      serializer.write(str.split(Const.DOT));
      return;
    }

    serializer.write(object);
  }
}

