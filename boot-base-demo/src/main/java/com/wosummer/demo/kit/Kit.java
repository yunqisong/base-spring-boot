package com.wosummer.demo.kit;

import cn.hutool.core.lang.Editor;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.wosummer.base.kit.UtilKit;
import com.wosummer.demo.generator.impl.PrefixIdGenerator;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author yunqisong
 * @for: Kit for
 * @date 2018/11/3
 */
@Slf4j
@SuppressWarnings("ALL")
public class Kit extends UtilKit {


  public static final String START = "[";
  private static final String MI = "MI";
  private static final String AR = "AR";


  public static JSON coolJson(JSON json, Editor<String> keyEditor) {
    Object retJson;
    if (json instanceof JSONObject) {
      JSONObject jsonObject = (JSONObject) json;
      JSONObject coolJson = new JSONObject();
      jsonObject.forEach((k, v) -> {
        if (keyEditor == null || (keyEditor.edit(k) != null)) {
          coolJson.put(CharUtil.isLetterUpper(k.charAt(0)) ? StrUtil.toCamelCase(k.toLowerCase()) : k, Kit.getValue(v, keyEditor));
        }
      });
      retJson = coolJson;
    } else {
      retJson = (JSON) ((JSONArray) json).stream().map(j -> getValue(j, keyEditor)).collect(Collectors.toCollection(JSONArray::new));
    }

    return (JSON) retJson;
  }

  public static Object getValue(Object v, Editor<String> keyEditor) {

    return JSONUtil.isJson(String.valueOf(v)) ? (Kit.coolJson(v instanceof JSON ? (JSON) v : (JSON) JSON.parse(v.toString()), keyEditor)) : v;
  }


  public static JSON deepStringToJSONObject(String jsonStr) {

    return deepStringToJSONObject(jsonStr, null);
  }

  public static JSON deepStringToJSONObject(String jsonStr, Editor<String> keyEditor) {
    return (JSON) getValue(jsonStr, keyEditor);
  }


  public static String idAble(String id) {

    return StrUtil.isNotBlank(id) ? id : IdWorker.getIdStr();
  }

  public static String saveToJsonStr(Object obj) {

    return Objects.isNull(obj) ? JSON.toJSONString(new JSONObject()) : JSONUtil.toJsonStr(obj);
  }

  public static String missionId() {
    return new PrefixIdGenerator(3, () -> MI).getIds(1)[0];
  }

  public static String articleId() {
    return new PrefixIdGenerator(3, () -> AR).getIds(1)[0];
  }


  public static String getNotBlankValue(String value, String defaultVale) {

    return StrUtil.isBlank(value) ? defaultVale : value;
  }
}

