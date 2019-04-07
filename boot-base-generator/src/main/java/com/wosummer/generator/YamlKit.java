package com.wosummer.generator;

import cn.hutool.core.util.ReUtil;
import org.ho.yaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * JKhaled created by yunqisong@foxmail.com 2017-11-12
 * FOR : yml 文件读取工具类
 *
 * @author yunqisong
 */
@SuppressWarnings("unchecked")
public class YamlKit {

  private static final Map<String, HashMap> CACHE = new HashMap<String, HashMap>();

  private static YamlKit yamlKit = new YamlKit();

  private static Map temp = null;

  private YamlKit() {
  }

  /**
   * 读取配置文件
   *
   * @param filePath
   * @return
   */
  public static YamlKit use(String filePath) {
    try {
      Map map = CACHE.get(filePath);
      if (map == null) {
        CACHE.put(filePath, Yaml.loadType(new File(filePath), HashMap.class));
      }
      temp = CACHE.get(filePath);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return yamlKit;
  }

  /**
   * 读取配置文件
   *
   * @param inputStream
   * @return
   */
  public static YamlKit use(InputStream inputStream) {
    try {
      Map map = CACHE.get(inputStream.toString());
      if (map == null) {
        CACHE.put(inputStream.toString(), Yaml.loadType(inputStream, HashMap.class));
      }
      temp = CACHE.get(inputStream.toString());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return yamlKit;
  }


  /**
   * 使用某个文件
   *
   * @param file
   * @return
   */
  public static YamlKit use(File file) {
    try {
      Map map = CACHE.get(file.getAbsolutePath());
      if (map == null) {
        CACHE.put(file.getAbsolutePath(), Yaml.loadType(file, HashMap.class));
      }
      temp = CACHE.get(file.getAbsolutePath());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return yamlKit;
  }

  /**
   * 根据 key 去取 文件的值
   *
   * @param key
   * @return
   */
  public static String getStr(String key) {
//        if (temp == null)
//            throw new MallYmlConfigException("请先使用use，不然宝宝真的不知道你要的配置文件是特么谁!! /(ㄒoㄒ)/~~");

    String[] keys = key.split(ReUtil.escape('.'));
    Map<String, Object> used = temp;
    for (int i = 0, keysLength = keys.length; i < keysLength; i++) {
      String k = keys[i];
      Object obj = used.get(k);
      if (i == keysLength - 1) {
        String retVal = String.valueOf(obj);
        if (retVal.contains("#")) {
          retVal = retVal.substring(0, retVal.indexOf("#"));
        }
        return retVal.trim();
      }
      if (obj instanceof Map) {
        used = (Map<String, Object>) obj;
      }
    }
    return null;
  }

  /**
   * 使用过 use 之后的 get
   *
   * @param key
   * @return
   */
  public String useAfterGetStr(String key) {
    return getStr(key);
  }

  /**
   * 使用过 use 之后的 getToLong
   *
   * @param key
   * @return
   */
  public Long userAfterGetLong(String key) {

    return Long.valueOf(useAfterGetStr(key));
  }
}
